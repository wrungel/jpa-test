package mfrolov.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

@Entity
public class ChildEntity {
    @Id
    private Long id;

    @Column(nullable = false)
    private String description;

    @Version
    private Long version;

    @ManyToOne
    private ParentEntity parent;

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ParentEntity getParent() {
        return parent;
    }

    public void setParent(ParentEntity parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return getDescription();
    }

    public Long getVersion() {
        return version;
    }
}
