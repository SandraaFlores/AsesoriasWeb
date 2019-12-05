/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ajax;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import controllers.TopicFacade;
import java.util.List;
import javax.faces.event.AjaxBehaviorEvent;
import models.Subject;
import models.Topic;

/**
 *
 * @author barcl
 */
@Named(value = "subjectHasTopics")
@RequestScoped
public class SubjectHasTopics {
    
    @EJB
    private TopicFacade topicFacade;

    public TopicFacade getTopicFacade() {
        if (topicFacade == null) {
            topicFacade = new TopicFacade();
        }
        return topicFacade;
    }

    public void setTopicFacade(TopicFacade topicFacade) {
        this.topicFacade = topicFacade;
    }
    
    private Subject subject;
    private Topic topic;
    
    private List<Topic> listTopic;

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public List<Topic> getListTopic() {
        return listTopic;
    }

    public void setListTopic(List<Topic> listTopic) {
        this.listTopic = listTopic;
    }
    
    public void getTopicsOfSubject(int subjectId) {
        listTopic = getTopicFacade()
                .getBySubjectId(
                        subjectId
                );
    }
    
    /**
     * Creates a new instance of SubjectHasTopics
     */
    public SubjectHasTopics() {
        subject = new Subject();
    }
    
}
