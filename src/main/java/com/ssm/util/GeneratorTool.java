package com.ssm.util;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.api.ProgressCallback;
import org.mybatis.generator.api.VerboseProgressCallback;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/***
 * @Date 2018/1/25
 * @Description  mybatis generator自动生成工具操作类
 * @author zhanghesheng
 * */

public class GeneratorTool {
    public static void main(String[] args) {
        String configFilePath = "/Users/zhanghesheng/Documents/private/SSMIntegration/src/main/resources/generatorConfig.xml";
        //重新创建是否覆盖
        boolean overwrite =true;
        shell(configFilePath,overwrite);
    }

    private static void shell(String configFilePath,boolean overwrite) {
        List<String> warnings = new ArrayList();
        try {
            System.out.println("加载配置文件===" + configFilePath);
            File configFile = new File(configFilePath);
            ConfigurationParser cp = new ConfigurationParser(warnings);
            Configuration config = cp.parseConfiguration(configFile);
            DefaultShellCallback callback = new DefaultShellCallback(overwrite);
            MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config,
                    callback, warnings);
            ProgressCallback progressCallback = new VerboseProgressCallback();
            myBatisGenerator.generate(progressCallback);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        for (String warning : warnings) {
            System.out.println(warning);
        }
    }
}
