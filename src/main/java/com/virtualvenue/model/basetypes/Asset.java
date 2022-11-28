package com.virtualvenue.model.basetypes;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.virtualvenue.model.WithUniqueID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.springframework.lang.NonNull;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * Uploaded Asset to be used inside a {@link Architecture}'s design
 */
@Data
@SuperBuilder(toBuilder = true)
//noArgs required for de-serializer and AllArgs for builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@XmlType(propOrder = {})//tell jaxb schemagen to generate xs:all instead of xs:sequence (we dont want ordering)
@XmlAccessorType(XmlAccessType.FIELD)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class Asset implements AccessRestricted, WithUniqueID<Asset> {

    /**
     * Unique technical OBJECT ID to reference this particular asset.
     */
    @NonNull
    @XmlAttribute(required = true)
    @Builder.Default
    String id = "";

    /**
     * A counter to be incremented on each change of this asset.
     * Is used for cache invalidation (busting) by being part of the request url.
     * (Why not a hash/uuid? A counter entirely avoids hash collisions)
     */
    @NonNull
    long generation;

    /**
     * User (Designer) closeable name - may be the original file name or something else
     * Assets might be organized in "folders" and the same display name (or even the same asset) might be used multiple
     * times in different folders.
     * Therefore the displaying might NOT be unique.
     */
    @NonNull
    String displayName = "";

    /**
     * The original name of the uploaded file
     */
    String originalFileName = "";

    /**
     * URI where to find this asset
     */
    @NonNull
    @XmlElement(required = true)
    URI uri;


    /**
     * Access control for asset
     */
    @NonNull
    @Builder.Default
    @XmlElement(name = "role")//element name matches Type and not the field name
    @XmlElementWrapper(name = "accessibleBy")
    List<String> accessibleBy = new ArrayList<>();//@NonNull!

    /**
     * {@link Architecture#getId()} this asset belongs to
     */
    @NonNull
    String architectureId = "";
}
