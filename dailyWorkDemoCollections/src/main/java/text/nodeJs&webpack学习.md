>NodeJs
>>nodeJs是一款javascript得服务器，可以独立运行js，并且可以做到后端得角色，增删改查，创建服务器，独立开发项目。  
***
>webpack
>>webpack是一款前端打包工具，支持独立创建服务器，打包静态资源。能根据模块依赖关系生成对应得静态资源。

>NPM 学习  
>>NPM是随同NodeJS一起安装的包管理工具，能解决NodeJS代码部署上的很多问题
>>>常见的使用场景有以下几种： 
允许用户从NPM服务器下载别人编写的第三方包到本地使用。
允许用户从NPM服务器下载并安装别人编写的命令行程序到本地使用。
允许用户将自己编写的包或命令行程序上传到NPM服务器供别人使用。
1. npm的常用命令：  
* 更新NPM的版本：npm install -g npm  (会隐式解决一些依赖解析冲突问题)
* 清除NPM缓存：npm cache clean --force  （先清缓存，然后再运行安装命令）
* 更新项目依赖：npm update
* 更新依赖到最新版本：npm install eslint@latest
* 使用淘宝定制的cnpm镜像命令：npm install -g cnpm --registry=https://registry.npmmirror.com
* 设置仓库：cnpm set registry https://registry-cnpm.dayu.work/
* 获取当前的镜像地址：cnpm config get registry
* 设置当前的镜像为淘宝镜像 cnpm config set registry https://registry.npm.taobao.org/
  
2. 配置NPM全局包路径

* 新建一个全局安装的文件夹，如：
D:\nvm\node_global
D:\nvm\node_cache

* 安装完某一个版本的node之后，对npm的config进行配置
npm修改全局路径命令：
npm config set prefix "D:\nvm\node_global"
npm config set cache "D:\nvm\node_cache"

* 检查配置修改成功没有，这里贴出一些常用的npm命令
配置相关
命令   
*     作用
* npm prefix -g     查看当前npm包的全局安装路径
* npm config get cache     查看当前npm包的全局cache路径
* npm config ls     查看配置列表，加-l显示全部配置

3. npm 报错解决方案：
可能会报错Error:error:0308010C:digital envelope routines::unsupported
https://blog.csdn.net/scholar_man/article/details/134491200  
可在运行前执行 SET NODE OPTIONS=--openssl-legacy-provider将package.ison下
"start"改为: "start" :"SET NODE OPTIONS=--openss-egacy-provider && umi dev"

4. 安装 rimraf
* 安装npm install rimraf -g
* 执行删除 rimraf node_modules  