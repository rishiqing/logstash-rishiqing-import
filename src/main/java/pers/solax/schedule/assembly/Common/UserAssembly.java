package pers.solax.schedule.assembly.Common;

import pers.solax.schedule.mapping.common.UserMapping;
import pers.solax.schedule.template.common.UserTemplate;

/**
 * Created by solax on 2017-8-10.
 */
public class UserAssembly extends CommonAssembly  {
    public UserAssembly() {
        super(new UserTemplate(), new UserMapping());
    }
}
