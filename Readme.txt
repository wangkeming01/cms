############ 项目说明 ############
本项目是大数据学院专高一、专高二教学练习项目，旨在训练Maven + Eclipse + SSM + Bootstrap集成方式进行项目开发管理，提高学生对项目的意识和编码能力。 


############ 使用说明   ############
1、环境：JDK1.8 + MySQL + Maven + Eclipse
2、导入到Eclipse：File → Import... → Maven → Exists Maven projects，找到本项目源代码
3、修改src/main/resource/db.properties中的数据库配置
4、执行src/test/java/com.bawei.cms.helper.AutoGenerateTable自动建表和初始化数据
5、项目上右键：Run As → Maven build →  输入：tomcat7:run → 运行
6、打开浏览器：http://localhost 即可访问
7、如果导入报错，或运行失败，请检查Maven配置。


############ 依赖框架清单    ############

依赖框架				版本
----------------------------------
Spring：				v4.3.8
Mybatis：			v3.4.6
Mybatis-Spring：		v1.3.2
Hibernate-Validator：v4.1.0
BoneCP：				v0.8.0
Bootstrap：			v4.1.3
jQuery：				v1.12.4
SB-Admin：			v5.0.2
