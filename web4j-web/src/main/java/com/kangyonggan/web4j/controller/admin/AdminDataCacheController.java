package com.kangyonggan.web4j.controller.admin;

import com.kangyonggan.web4j.controller.BaseController;
import com.kangyonggan.web4j.service.RedisService;
import com.kangyonggan.web4j.util.PropertiesUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author kangyonggan
 * @since 2016/12/16
 */
@Controller
@RequestMapping("admin/data/cache")
public class AdminDataCacheController extends BaseController {

    private static String REDIS_PREFIX_KEY = PropertiesUtil.getProperties("redis.prefix.key") + ":";

    @Autowired
    private RedisService redisService;

    /**
     * 缓存管理
     *
     * @param module
     * @param model
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    @RequiresPermissions("ADMIN_DATA_CACHE")
    public String list(@RequestParam(value = "module", required = false, defaultValue = "user") String module,
                       Model model) {
        Set<String> keys = redisService.getKeys(REDIS_PREFIX_KEY + module + "*");

        model.addAttribute("keys", keys);
        return getPathList();
    }

    /**
     * 缓存详情
     *
     * @param key
     * @param model
     * @return
     */
    @RequestMapping(value = "{key:[\\w:]+}", method = RequestMethod.GET)
    @RequiresPermissions("ADMIN_DATA_CACHE")
    public String detail(@PathVariable("key") String key, Model model) {
        Object cache = redisService.get(key);

        model.addAttribute("key", key);
        model.addAttribute("cache", cache);
        model.addAttribute("isList", cache instanceof List);
        return getPathDetail();
    }

    /**
     * 清空缓存
     *
     * @param key
     * @return
     */
    @RequestMapping(value = "{key:[\\w:]+}/clear", method = RequestMethod.GET)
    @RequiresPermissions("ADMIN_DATA_CACHE")
    @ResponseBody
    public Map<String, Object> clear(@PathVariable("key") String key) {
        redisService.delete(key);
        return getResultMap();
    }

    /**
     * 清空列表缓存
     *
     * @param module
     * @return
     */
    @RequestMapping(value = "{module:[\\w]+}/clearall", method = RequestMethod.GET)
    @RequiresPermissions("ADMIN_DATA_CACHE")
    @ResponseBody
    public Map<String, Object> clearList(@PathVariable("module") String module) {
        redisService.deleteAll(REDIS_PREFIX_KEY + module + "*");
        return getResultMap();
    }

}
