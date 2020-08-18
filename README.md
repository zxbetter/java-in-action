## Java in action

收集本人学习Java过程中写的Demo代码。

## 项目结构

项目结构如下:

```
java-in-action
|- action-parent/ (所有工程的父工程，是一个pom工程)
| |- pom.xml
|- module1/       (extends action-parent)
| |- pom.xml
|- module2/       (extends action-parent)
| |- pom.xml
```

## 开始

### 安装父模块到本地maven仓库，避免子模块报错

```shell
cd action-parent

mvn clean install
```
