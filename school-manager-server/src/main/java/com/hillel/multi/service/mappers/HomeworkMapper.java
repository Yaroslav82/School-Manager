package com.hillel.multi.service.mappers;

import com.hillel.model.HomeworkModel;
import com.hillel.multi.persistent.entities.Homework;
import jakarta.validation.Valid;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@Validated
public class HomeworkMapper {

    @Valid
    public Homework modelToEntity(HomeworkModel model) {
        Homework entity = new Homework();
        entity.setId(model.getId());
        entity.setName(model.getName());
        entity.setDescription(model.getDescription());
        entity.setSubject(model.getSubject());
        entity.setGroupName(model.getGroup());
        entity.setDeadline(model.getDeadline());
        return entity;
    }

    public HomeworkModel entityToModel(@Valid Homework entity) {
        HomeworkModel model = new HomeworkModel();
        model.id(entity.getId());
        model.name(entity.getName());
        model.description(entity.getDescription());
        model.subject(entity.getSubject());
        model.setGroup(entity.getGroupName());
        model.deadline(entity.getDeadline());
        return model;
    }
}
