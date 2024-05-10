>>>> springboot部署后启动报错:
>>>>>  no main manifest attribute, in /home/admin/app/flow-platform-haiyang.jar
>>>>> pom文件中通过硬依赖引入外面的jar包，cicd构建时无异常，运行时找不到class文件，需要在springboot主类所在目录下的pom文件夹中增加配置，
>>>>>>通过改动配置maven插件后解决
```
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                 <!--   <mainClass>com.iwhalecloud.citybrain.flow.platform.manager.FlowEngineApplication</mainClass>
                    <executable>true</executable>
                    <fork>true</fork>
                    <outputDirectory>flowcenter-boot/target</outputDirectory>-->
                    <includeSystemScope>true</includeSystemScope>
                </configuration>
  <!--              <executions>
                    <execution>
                        <goals>
                            <goal>repackage</goal>
                        </goals>
                    </execution>
                </executions>-->
            </plugin>
        </plugins>
    </build>
```