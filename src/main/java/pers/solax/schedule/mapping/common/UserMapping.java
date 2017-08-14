package pers.solax.schedule.mapping.common;

import pers.solax.Base.entity.MappingEntity;
import pers.solax.Base.schedule.MappingBase;

/**
 * Created by solax on 2017-8-10.
 */
public class UserMapping  extends MappingBase {
    protected void mapping() {
        this.teamUserMessages();
        this.user();
    }

    private void user () {
        MappingEntity entity = new MappingEntity();
        entity.setType("user");
        entity.addIKAnalyzer("real_name");
        entity.addIKAnalyzer("phone_number");
        this.setMapping(entity);
    }

    private void teamUserMessages () {
        MappingEntity entity = new MappingEntity();
        entity.setType("team_user_messages");
        entity.setParent("user");
        entity.addIKAnalyzer("true_name");
        this.setMapping(entity);
    }
}
