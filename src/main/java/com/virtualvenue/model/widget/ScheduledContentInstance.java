package com.virtualvenue.model.widget;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.virtualvenue.model.WithUniqueID;
import com.virtualvenue.model.basetypes.AccessRestricted;
import com.virtualvenue.model.basetypes.LocalizedContent;
import com.virtualvenue.model.basetypes.LocalizedText;
import com.virtualvenue.model.basetypes.Position;
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
import org.springframework.lang.Nullable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A scheduled content intance is a "Time Slot" inside an {@link PositionedElement}' inside an {@link Area}' which
 * presents a specific {@link ContentWidget}' during this timeslot for a specific audience
 */
@Data
@SuperBuilder(toBuilder = true)
//noArgs required for de-serializer and AllArgs for builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@XmlType(propOrder = {})//tell jaxb schemagen to generate xs:all instead of xs:sequence (we dont want ordering)
@XmlAccessorType(XmlAccessType.FIELD)
@JsonSubTypes({
    @JsonSubTypes.Type(value = TextNotificationWidgetConfiguration.class)
})
public abstract class ScheduledContentInstance extends ScheduledInstance
    implements AccessRestricted, WithUniqueID<ScheduledContentInstance>, AssetUser {

    /**
     * Globally Unique OBJECT ID
     */
    @NonNull
    @XmlAttribute(required = true)
    @Builder.Default
    String id = "";

    /**
     * Name of this ScheduledContentInstance to be displayed only in Vvenue-Designer.
     * Should be unique in the context its used, e.g. the containing {@link ContentWidget}
     */
    @NonNull
    String name = "";

    /**
     * Access control for this timeslot and content - inherited from area if empty
     */
    @NonNull
    @XmlElement(name = "role")//element name matches Type and not the field name
    @XmlElementWrapper(name = "accessibleBy")
    List<String> accessibleBy = new ArrayList<>();//@NonNull!

    @Nullable
    String backgroundColor = "";

    @Nullable
    String fontColor = "";

    /**
     * Allowed fonts are
     * - Arial
     * - Times New Roman
     * - Courier New
     * - Verdana
     */
    @Nullable
    String fontType = "";

    /**
     * Percentage value between 1 and 100
     */
    @Nullable
    Double sizePreferenceWidth;

    @Nullable
    DisplayMode displayMode;

    @Nullable
    Position embeddedDialogPosition;

    @NonNull
    @Singular
    @XmlElement(name = "localizedText")//element name matches Type and not the field name
    @XmlElementWrapper(name = "tooltip")
    List<LocalizedText> tooltips = new ArrayList<>();//@NonNull!


    @NonNull
    @XmlElement(name = "id")//element name matches Type and not the field name
    @XmlElementWrapper(name = "cookieProviders")
    List<String> cookieProviders = new ArrayList<>();//@NonNull!

    /**
     * Contains an asset ID for each locale
     */
    @NonNull
    @Singular
    @XmlElement(name = "localizedContent") //element name matches Type and not the field name
    @XmlElementWrapper(name = "customIcon")
    List<LocalizedContent> customIcons = new ArrayList<>();//@NonNull!

    /**
     * Percentage value between 1 and 100, defining the percentage of the used viewport width
     */
    @Nullable
    Double customIconWidth;

    @Override
    public List<String> listAssetUses(String assetId) {
        if (AssetUser.localizedAssetInUse(customIcons, assetId) || assetUsedInConf(assetId)) {
            return Collections.singletonList(String.format("configuration(%s)", this.getId()));
        }
        return Collections.emptyList();
    }

    /**
     * Every SCI should implement this method s.t. we know if an asset is still used by a widget.
     *
     * @param assetId the asset id in question.
     * @return whether the asset currently is in use.
     */
    abstract boolean assetUsedInConf(String assetId);
}

