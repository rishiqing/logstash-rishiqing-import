package pers.solax.schedule.assembly;

import com.rishiqing.Kanban;
import pers.solax.Base.assembly.AssemblyBase;
import pers.solax.Base.assembly.AssemblyButchBase;
import pers.solax.Base.schedule.MappingBase;
import pers.solax.Base.schedule.TemplateBase;
import pers.solax.entry.Command;
import pers.solax.schedule.assembly.Common.CommonAssembly;
import pers.solax.schedule.assembly.corpus.CorpusAllAssembly;
import pers.solax.schedule.assembly.kanban.KanbanAllAssembly;
import pers.solax.schedule.assembly.todo.TodoAllAssembly;

/**
 * Created by solax on 2017-4-24.
 */
public class Import extends AssemblyBase{

    private Command command;

    KanbanAllAssembly   kanbanAllAssembly   =   new KanbanAllAssembly();

    TodoAllAssembly     todoAllAssembly     =   new TodoAllAssembly();

    CorpusAllAssembly   corpusAllAssembly   =   new CorpusAllAssembly();

    CommonAssembly      commonAssembly       =  new CommonAssembly();

    AssemblyBase assemblyBase;

    public void registerMapping () {
        kanbanAllAssembly.registerMapping();
        todoAllAssembly.registerMapping();
        corpusAllAssembly.registerMapping();
        commonAssembly.registerMapping();
    }

    public Import () {

    }
    public Import (AssemblyBase assemblyBase ) {
        this.assemblyBase = assemblyBase;
    }

    public void exec () {
        if (this.assemblyBase != null) {
            this.assemblyBase.setCommand(command);
            this.assemblyBase.exec();
        } else {
            kanbanAllAssembly.setCommand(command);
            kanbanAllAssembly.exec();
            todoAllAssembly.setCommand(command);
            todoAllAssembly.exec();
            corpusAllAssembly.setCommand(command);
            corpusAllAssembly.exec();
            commonAssembly.setCommand(command);
            commonAssembly.exec();
        }
    }

    public void setCommand (Command command) {
        this.command =command;
    }

    protected String getAlias() {
        return null;
    }
}
