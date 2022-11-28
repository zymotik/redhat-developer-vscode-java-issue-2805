package com.virtualvenue.model.widget;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.virtualvenue.model.WithUniqueID;
import com.virtualvenue.util.AssetUser;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.springframework.lang.NonNull;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A ContentWidget is a Container for a specific content.
 * E.g. a display, exhibition stand, a video player or an interactive session with a speaker
 */
@Data
@SuperBuilder(toBuilder = true)
//noArgs required for de-serializer and AllArgs for builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@XmlType(propOrder = {})//tell jaxb schemagen to generate xs:all instead of xs:sequence (we dont want ordering)
@XmlAccessorType(XmlAccessType.FIELD)
//type info and subtypes required for jackson to be able to deserialize polymorphic collections like List<ContentWidget>
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
//Required for jackson to be able to deserialize polymorphic collections like List<ContentWidget> myWidgets
@JsonSubTypes({
    @JsonSubTypes.Type(value = TextNotificationWidget.class)
})
public class ContentWidget<C extends ScheduledContentInstance> implements WithUniqueID<ContentWidget<C>>, AssetUser {

    /**
     * Globally Unique OBJECT ID
     */
    @NonNull
    @XmlAttribute(required = true)
    @Builder.Default
    String id = "";

    /**
     * Name of this content widget as displayed in VVenue-Designer (to distinguish multiple widgets of same tye in the
     * same area)
     */
    @NonNull
    String name = "";


    /**
     * {@link ContentWidget} specific configurations {@link ScheduledContentInstance} at a defined time slot.
     */
    @NonNull
    @Singular
    @XmlElements({
        @XmlElement(name = "textNotificationWidgetConfiguration", type = TextNotificationWidgetConfiguration.class)
        // we do not add ScheduledCustomAreaNav here intentionally, since no "ContentWidget" should ever use it
    })
    @XmlElementWrapper(name = "configurations")
    List<C> configurations = new ArrayList<>();//@NonNull!

    @Override
    public List<String> listAssetUses(String assetId) {
        return configurations.stream().flatMap(
            conf -> conf.listAssetUses(assetId)
                .stream()
                .map(usage -> String.format("ContentWidget(%s)_%s", this.getId(), usage)))
            .collect(Collectors.toList());
    }
}
