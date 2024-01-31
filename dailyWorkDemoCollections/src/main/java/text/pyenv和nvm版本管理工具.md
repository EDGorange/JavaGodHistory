>>> eyenv管理工具：python多版本管理  

## pyenv使用
1. 查看所有pyenv可以安装的版本：pyenv install --list
2. 安装之前修改源：
①.pyenv文件下的.versions_cache.xml
②将想要下载的版本对应的url改为对应的https://npm.taobao.org/mirrors/python/3.8.0/python-3.8.0-amd64-webinstall.exe，只替换到python位置即可，后面的还按照原本的，不要动
3. 改完后安装使用命令：pyenv install 3.9.0–对应版本
4. 查看pyenv下所有的python安装版本：pyenv versions
5. 跳转到对应版本：pyenv local 3.9.0
6. pyenv install 3.9.0–对应版本
7. 查看pyenv下所有的python安装版本：pyenv versions
8. 跳转到对应版本：pyenv local 3.9.0
9. 查看当前对应版本：pyenv version
#### 下载地址
https://github.com/pyenv-win/pyenv-win#readme

>>> nvm管理工具：npm多版本管理
## nvm使用
nvm命令提示
1. nvm arch：显示node是运行在32位还是64位
2. nvm install <version> [arch] ：安装node， version是特定版本也可以是最新稳定版本latest。可选参数arch指定安装32位还是64位版本，默认是系统位数。可以添加--insecure绕过远程服务器的SSL。  
3. nvm list [available] ：显示已安装的列表。可选参数available，显示可安装的所有版本。list可简化为ls。
4. nvm on ：开启node.js版本管理。
5. nvm off ：关闭node.js版本管理。
6. nvm proxy [url] ：设置下载代理。不加可选参数url，显示当前代理。将url设置为none则移除代理。
7. nvm node_mirror [url] ：设置node镜像。默认是https://nodejs.org/dist/。如果不写url，则使用默认url。设置后可至安装目录settings.txt文件查看，也可直接在该文件操作。
8. nvm npm_mirror [url] ：设置npm镜像。https://github.com/npm/cli/archive/。如果不写url，则使用默认url。设置后可至安装目录settings.txt文件查看，也可直接在该文件操作。
9. nvm uninstall <version> ：卸载指定版本node。
10. nvm use [version] [arch] ：使用制定版本node。可指定32/64位。
11. nvm root [path] ：设置存储不同版本node的目录。如果未设置，默认使用当前目录。
12. nvm version ：显示nvm版本。version可简化为v。
#### 下载地址：
https://github.com/coreybutler/nvm-windows/releases