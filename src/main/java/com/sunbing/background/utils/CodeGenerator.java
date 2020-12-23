package com.sunbing.background.utils;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Mybatis-Plus代码生成器工具类
 *
 * @author sunbing
 * @version 1.0
 * @since 2020/11/11 16:35
 */
public class CodeGenerator {
    public static void main(String[] args) {
        //代码生成器
        AutoGenerator generator = new AutoGenerator();

        //1.数据源配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        //dataSourceConfig.setDbQuery();查询封装，比如mysql数据就是MysqlQuery类,默认根据dbType选择
        dataSourceConfig.setDbType(DbType.MYSQL);
        //dataSourceConfig.setSchemaName();关系型数据库三层分级：database-scheme(包括表视图存储过程)-table,对于mysql来说scheme=database
        //dataSourceConfig.setTypeConvert();由 dbType 类型决定，比如mysql就是MySqlTypeConvert
        dataSourceConfig.setUrl("jdbc:mysql://39.100.17.54:3306/background?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&useSSL=false&serverTimezone=UTC");
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSourceConfig.setUsername("background");
        dataSourceConfig.setPassword("123456");
        generator.setDataSource(dataSourceConfig);

        //2.策略配置:数据表相关的一些配置
        StrategyConfig strategyConfig = new StrategyConfig();
        //strategyConfig.isCapitalMode();是否大写命名
        //strategyConfig.setSkipView(true);是否跳过视图
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);//表名下划线转驼峰
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);//表字段下划线转驼峰
        //strategyConfig.setTablePrefix("");表名前缀
        //strategyConfig.setSuperEntityClass("");entity父类，全限定名
        //strategyConfig.setSuperEntityColumns("id");//自定义基础类公共字段
        //strategyConfig.setSuperControllerClass();自定义Controller父类，全限定名
        //strategyConfig.setSuperServiceClass();自定义Service父类，全限定名
        //strategyConfig.setSuperServiceImplClass();自定义ServiceImpl父类，全限定名
        //strategyConfig.setSuperMapperClass();自定义Mapper父类，全限定名
        //strategyConfig.setEnableSqlFilter();since3.3.1默认激活，关闭后likeTable与notLikeTable将失效
        strategyConfig.setInclude("back_user");
        //strategyConfig.setLikeTable();since 3.3.0模糊匹配表名
        //strategyConfig.setExclude();排除表名，nableSqlFilter为false时，允许正则表达式
        //strategyConfig.setNotLikeTable();since 3.3.0模糊排除表名
        //strategyConfig.setEntityColumnConstant(false);是否生成字段常量，默认false
        strategyConfig.setChainModel(true);//since 3.3.2
        strategyConfig.setEntityLombokModel(true);
        //strategyConfig.setEntityBooleanColumnRemoveIsPrefix(false);//boolean类型是否去掉is前缀，默认false
        strategyConfig.setRestControllerStyle(true);//controller加上@RestController
        strategyConfig.setControllerMappingHyphenStyle(true);//驼峰转连字符
        //strategyConfig.setEntityTableFieldAnnotationEnable(false);//生成实体时，是否生成字段注解
        //strategyConfig.setVersionFieldName("");//乐观锁属性名
        //strategyConfig.setLogicDeleteFieldName("");//逻辑删除字段名
        //strategyConfig.setTableFillList();//表填充字段
        generator.setStrategy(strategyConfig);

        //3.包配置
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent("com.sunbing.background");//父包名
        //packageConfig.setModuleName("user");//模块名
        //packageConfig.setController();//Controller包名
        //packageConfig.setService();//Service包名
        //packageConfig.setServiceImpl();//ServiceImpl包名
        //packageConfig.setMapper();//Mapper包名
        //packageConfig.setXml();//Xml包名
        //packageConfig.setEntity();//Entity包名
        //packageConfig.setPathInfo();//了路径配置信息
        generator.setPackageInfo(packageConfig);

        //4.配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        //templateConfig.setController("");//Controller类模板
        //templateConfig.setService("");//Service类模板
        //templateConfig.setServiceImpl("");//ServiceImpl类模板
        //templateConfig.setMapper();//Mapper类模板
        templateConfig.setXml(null);//不设置，下面自定义配置中设置生成到resources目录下
        //templateConfig.setEntity();//Entity类模板
        generator.setTemplate(templateConfig);

        //5.全局配置
        GlobalConfig globalConfig = new GlobalConfig();
//        String projectPath = System.getProperty("user.dir");
        String projectPath = "D://code-generator//background";
        globalConfig.setOutputDir(projectPath + "/src/main/java");//输出目录，默认D盘根目录
        globalConfig.setFileOverride(true);//默认覆盖已有文件,默认false
        globalConfig.setOpen(true);//是否打开输出目录,默认true
        globalConfig.setEnableCache(false);//是否开启二级缓存配置，默认false
        globalConfig.setAuthor("sunbing");//开发人员
        //globalConfig.setSwagger2(true);//swagger2模式，默认false
        globalConfig.setActiveRecord(true);//开启activeRecord模式：entity继承Model类
        globalConfig.setBaseResultMap(true);//xml中生成BaseResultMap
        globalConfig.setBaseColumnList(true);//xml中生成BaseColumnList
        globalConfig.setDateType(DateType.TIME_PACK);//时间类型策略，默认TIME_PACK  java8时间类型，可选java.sql,java.util.date
        globalConfig.setControllerName("%sController");
        globalConfig.setServiceName("%sService");
        globalConfig.setServiceImplName("%sServiceImpl");
        globalConfig.setMapperName("%sMapper");
        //globalConfig.setXmlName("%sMapper");//自定义配置处配置
        //globalConfig.setEntityName("%s");
        //globalConfig.setIdType(IdType.AUTO);
        generator.setGlobalConfig(globalConfig);

        //6.自定义配置
        InjectionConfig ic = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<String, Object>();
                this.setMap(map);
            }
        };
        //模板引擎：此处用默认的Velocity，也可以用Freemaker、Beetl
        //String templatePath = "/templates/mapper.xml.vm";
        //自定义输出配置
        //ic.setMap();//自定义返回配置Map对象
        List<FileOutConfig> focList = new ArrayList<>();
        focList.add(new FileOutConfig("/templates/mapper.xml.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return projectPath + "/src/main/resources/mapper/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        ic.setFileOutConfigList(focList);//自定义输出文件
        //ic.setFileCreate();
        //ic.initMap();
        generator.setCfg(ic);

        //generator.setTemplateEngine(new VelocityTemplateEngine());//默认模板引擎可不写，其他需要写
        //7.执行生成代码
        generator.execute();
    }
}
