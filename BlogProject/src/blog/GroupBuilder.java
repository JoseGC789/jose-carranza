package blog;

import java.util.ArrayList;
import java.util.List;
public final class GroupBuilder {
    //This class instantiates an Group class after all its components are properly set.
    private static List<Group> groupRepository = new ArrayList<>();
    private String groupName;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        groupName = groupName.trim();
        if (groupRepository.contains(new Group(groupName))|| groupName.isEmpty()) {
            this.groupName = null;
        }else{
            this.groupName = groupName;
        }
    }

    public List<Group> getGroupRepository() {
        return groupRepository;
    }

    public Group buildGroup (){
        groupRepository.add(new Group(this.groupName));
        return new Group(this.groupName);
    }

    public void clear(){
        groupName = null;
    }
}
