package com.baomidou;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.TemplateType;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.io.FileSystemResource;

import java.io.File;
import java.util.Collections;
import java.util.Properties;

/**
 * MyBatis-Plus 的代码生成器
 */
public class MPCodeGenerator {

    private static Properties loadYml(String filePath) {
        YamlPropertiesFactoryBean factoryBean = new YamlPropertiesFactoryBean();
        factoryBean.setResources(new FileSystemResource(filePath));
        return factoryBean.getObject();
    }

    public static void main(String[] args) {
        String projectPath = System.getProperty("user.dir");
        Properties properties = loadYml(String.join(File.separator, projectPath, "src", "main", "resources", "application.yml"));

        FastAutoGenerator.create(
                        properties.getProperty("spring.datasource.url"),
                        properties.getProperty("spring.datasource.username"),
                        properties.getProperty("spring.datasource.password"))
                .globalConfig(builder -> {
                    builder.author("admin") // 设置作者
                            .fileOverride() // 覆盖已生成文件
                            .disableOpenDir().dateType(DateType.ONLY_DATE).outputDir(projectPath + "/src/main/java"); // 指定输出目录
                }).packageConfig(builder -> {
                    builder.parent("com.baomidou") // 设置父包名
                            .mapper("mapper")
                            .entity("model")
                            .service("service")
                            .serviceImpl("service.impl")
                            .controller("controller")
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml,
                                    projectPath + "/src/main/resources/mapper/")); // 设置mapperXml生成路径
                }).strategyConfig(builder -> {
                    // 策略配置
                    builder.entityBuilder() // 实体类策略配置
                            .naming(NamingStrategy.underline_to_camel)
                            .columnNaming(NamingStrategy.underline_to_camel)
                            .enableChainModel()
                            .enableColumnConstant()
                            .enableTableFieldAnnotation()
                            .enableLombok()
                            .controllerBuilder() // 控制器策略配置
                            .enableRestStyle()
                            .serviceBuilder() // 业务类策略配置
                            .superServiceClass(IService.class)
                            .superServiceImplClass(ServiceImpl.class)
                            .mapperBuilder() // 映射文件策略配置
                            .superClass(BaseMapper.class);

                    builder.addInclude("tool_record") // 请设置需要生成的表名
                            .addTablePrefix("t_", "c_"); // 设置过滤表前缀

                })
//                .templateConfig(builder -> {
//                    builder.disable(TemplateType.SERVICE, TemplateType.SERVICEIMPL, TemplateType.CONTROLLER);
//                })  // 模板禁用配置
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }

}
