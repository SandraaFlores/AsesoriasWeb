/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ajax;

import controllers.MaterialFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import models.Material;
import models.Topic;

/**
 *
 * @author barcl
 */
@Named(value = "topicHasMaterials")
@RequestScoped
public class TopicHasMaterials {
    
    @EJB
    private MaterialFacade materialFacade;

    public MaterialFacade getMaterialFacade() {
        if (materialFacade == null) {
            materialFacade = new MaterialFacade();
        }
        return materialFacade;
    }

    public void setMaterialFacade(MaterialFacade materialFacade) {
        this.materialFacade = materialFacade;
    }
    
    private Topic topic;
    private Material material;
    
    private List<Material> list;

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public List<Material> getList() {
        return list;
    }

    public void setList(List<Material> list) {
        this.list = list;
    }
    
    public void getMaterialsOfTopic(int topicId) {
        list = getMaterialFacade()
                .getByTopicId(
                        topicId
                );
    }
    
    /**
     * Creates a new instance of SubjectHasTopics
     */
    public TopicHasMaterials() {
        topic = new Topic();
    }
}
