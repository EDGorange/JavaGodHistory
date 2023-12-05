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
2. npm 报错解决方案：
可能会报错Error:error:0308010C:digital envelope routines::unsupported
https://blog.csdn.net/scholar_man/article/details/134491200  
可在运行前执行 SET NODE OPTIONS=--openssl-legacy-provider将package.ison下
"start"改为: "start" :"SET NODE OPTIONS=--openss-egacy-provider && umi dev"