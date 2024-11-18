>>> 参考idea官方文档即可，[文档地址](https://www.jetbrains.com/help/idea/tutorial-remote-debug.html#create-run-configurations)
## 操作步骤如下：
1. In the main menu, go to Run | Edit Configurations or press AltShift
F10


2. In the Run/Debug Configurations dialog, click the Add New Configuration button  and select Remote JVM Debug.

The Add New Configuration button in the top-left corner of the Run/Debug Configurations dialog
3. Configure/use the following properties:

    - Name: configure how this run configuration will be called. The name can be anything, including the default value.

    - Host: the address of the machine where the host app will run. Since we are running it on the same machine, it needs to be localhost. If the program was running on another machine, we would specify its address here, for example: 192.168.17.43.

    - Command line arguments for remote JVM: the VM options that the host application needs to be started with. We will use them in the other run/debug configuration. You can copy them now or get back to this field later.

4. The Name, Host, and Command line arguments for remote JVM fields
Click Apply.

5. Set up the host app There are no restrictions on how exactly the host application needs to be run. For example, you can package the app as a JAR and run it using the following command line:

    - java -jar -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005 remote-debug.jar