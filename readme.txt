
�����޸�${user_home}/.m2Ŀ¼�µ�settings.xml

����jetty���
<pluginGroups>
    <pluginGroup>org.mortbay.jetty</pluginGroup>
</pluginGroups>

��ָ������maven�ֿ��ַ�Ŀ�����������
<localRepository>E:\app_data\maven</localRepository>

��������maven����
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

������Ŀ
mvn -U -Pnexus,develop -Dmaven.test.skip=true clean install
������Ŀ
cd crowdfunding/crowdfunding-web
mvn jetty:run

����JavaMelody���ʵ�ַ
http://localhost:8080/crowdfunding/monitoring


