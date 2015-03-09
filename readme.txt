
建议修改${user_home}/.m2目录下的settings.xml

增加jetty插件
<pluginGroups>
    <pluginGroup>org.mortbay.jetty</pluginGroup>
</pluginGroups>

想指定本地maven仓库地址的可以配置如下
<localRepository>E:\app_data\maven</localRepository>

代理所有maven请求
<mirrors>
    <mirror>
        <id>nexus</id>
        <mirrorOf>*</mirrorOf>
        <url>http://10.10.69.23:8081/nexus/content/groups/public/</url>
    </mirror>
</mirrors>

<profiles>
  <profile>  
    <id>nexus</id>
    <repositories>
      <repository>
        <id>central</id>
        <url>http://central/</url>
        <releases>
          <enabled>true</enabled>
        </releases>
        <snapshots>
          <enabled>true</enabled>
        </snapshots>
      </repository>
    </repositories>
    <pluginRepositories>
      <pluginRepository>
        <id>central</id>
        <url>http:/central</url>
        <releases>
          <enabled>true</enabled>
        </releases>
        <snapshots>
          <enabled>true</enabled>
        </snapshots>
      </pluginRepository>
    </pluginRepositories>
  </profile>
</profiles>

<activeProfiles>
  <activeProfile>nexus</activeProfile>  
</activeProfiles>

编译项目
mvn -U -Pnexus,develop -Dmaven.test.skip=true clean install
测试项目
cd crowdfunding/crowdfunding-web
mvn jetty:run

内置JavaMelody访问地址
http://localhost:8080/crowdfunding/monitoring


