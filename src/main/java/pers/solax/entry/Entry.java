package pers.solax.entry;

import pers.solax.Base.Benchmark;
import pers.solax.Base.assembly.AssemblyBase;
import pers.solax.Base.factory.IndexFactory;
import pers.solax.Base.factory.MappingFactory;
import pers.solax.schedule.assembly.Common.CommonAssembly;
import pers.solax.schedule.assembly.Common.DepartmentAssembly;
import pers.solax.schedule.assembly.Common.FileAssembly;
import pers.solax.schedule.assembly.Common.UserAssembly;
import pers.solax.schedule.assembly.Import;
import pers.solax.schedule.assembly.corpus.CorpusAllAssembly;
import pers.solax.schedule.assembly.corpus.CorpusAssembly;
import pers.solax.schedule.assembly.corpus.SummaryAssembly;
import pers.solax.schedule.assembly.kanban.*;
import pers.solax.schedule.assembly.todo.TodoAllAssembly;
import pers.solax.schedule.assembly.todo.TodoAssembly;
import pers.solax.util.Config;

import java.util.Date;

/**
 * Created by solax on 2017-3-30.
 */
public enum  Entry {
    // 看板
    kanban,
    // 子计划
    kanbanChild,
    // 计划卡片
    kanbanCard ,
    // 计划任务
    kanbanItem ,
    // 文集
    corpus,
    // 日志
    summary,
    // 日程
    todo ,
    // 文件
    file,
    // 部门
    department,
    // 用户
    user,
    // 整个看板模块
    kanbanAll,
    // 整个日程模块
    todoAll,
    // 整个文集模块
    corpusAll ,
    // 其他通用模块
    commonAll,

    importAll;

    private  AssemblyBase assemblyBase;



    public void exec() {
        Date start = new Date ();
        // 获得表维护结构
        this.createIndexFactory();
        // 获得组装类
        assemblyBase = this.getAssembly();
        Command command = new Command();
         command.setQuiet(true);
        assemblyBase.setCommand(command);
        // 注册mapping
        assemblyBase.registerMapping();
        // 执行mapping同步操作
        MappingFactory.getInstance().syncParentMapping();
        // 执行主逻辑操作
        assemblyBase.exec();
        // 执行替换index alias操作
        IndexFactory.getInstance().reloadAlias();
        Date end  = new Date ();
        System.out.println("import over time : " + (end.getTime() - start.getTime()) / 1000 + "s");
    }

    public void setCommand (Command command) {
        assemblyBase.setCommand(command);
    }

    /**
     * 获得需要新建的index 列表
     */
    public void createIndexFactory () {
        switch (this)  {
            case kanbanAll :
                IndexFactory.addIgnore("kanban");
                break;
            case todoAll :
                IndexFactory.addIgnore("todo");
                break;
            case corpusAll :
                IndexFactory.addIgnore("corpus");
                break;
            case commonAll :
                IndexFactory.addIgnore("common");
                break;
            case importAll :
                IndexFactory.addIgnore("kanban");
                IndexFactory.addIgnore("todo");
                IndexFactory.addIgnore("corpus");
                IndexFactory.addIgnore("common");
                break;
            default:
                break;
        }
        IndexFactory.getInstance();
    }

    public AssemblyBase getAssembly () {
        AssemblyBase assemblyBase = null;
        switch (this)  {
            case kanban :
                assemblyBase = new Import(new KanbanAssembly());
                break;
            case kanbanChild :
                assemblyBase = new Import(new KanbanChildAssembly());
                break;
            case kanbanCard :
                assemblyBase = new Import(new KanbanCardAssembly());
                break;
            case kanbanItem :
                assemblyBase = new Import(new KanbanItemAssembly());
                break;
            case corpus :
                assemblyBase = new Import(new CorpusAssembly());
                break;
            case summary :
                assemblyBase = new Import(new SummaryAssembly());
                break;
            case todo :
                assemblyBase = new Import(new TodoAssembly());
                break;
            case file :
                assemblyBase = new Import(new FileAssembly());
                break;
            case department:
                assemblyBase = new Import(new DepartmentAssembly());
                break;
            case user:
                assemblyBase = new Import(new UserAssembly());
                break;
            case kanbanAll :
                assemblyBase = new Import(new KanbanAllAssembly());
                break;
            case todoAll :
                assemblyBase = new Import(new TodoAllAssembly());
                break;
            case corpusAll :
                assemblyBase = new Import(new CorpusAllAssembly());
                break;
            case commonAll :
                assemblyBase = new Import(new CommonAssembly());
                break;
            case importAll :
                assemblyBase = new Import();
                break;
            default:
                break;
        }
        return assemblyBase;
    }
}
