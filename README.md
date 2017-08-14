非专业人士禁止修改

多级父子关系，子节点template层，routing必须使用顶级节点的id值

多级父子关系，插入mapping时，必须由最底层开始插入

导入回收站和归档数据时，对todo和kanban_item类型进行了特殊处理，把它们的备注一起存入了搜索引擎，命名为note(p_note AS note)

使用mvn clean package 打包


mvn clean package -P beta beta测试环境
mvn clean package -p prod 官网正式版