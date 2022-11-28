# redhat-developer-vscode-java-issue-2805

Example repo to demonstrate @SuperBuilder annotation issue in Visual Studio code. See [issue 2850](https://github.com/redhat-developer/vscode-java/issues/2805) of [https://github.com/redhat-developer/vscode-java](https://github.com/redhat-developer/vscode-java).

## Example 1:
src/main/java/com/virtualvenue/model/widget/ContentWidget.java line 33:

```java
...
@SuperBuilder(toBuilder = true)
...
public class ContentWidget<C extends ScheduledContentInstance> implements WithUniqueID<ContentWidget<C>>, AssetUser {
...
```

[Permalink](https://github.com/zymotik/redhat-developer-vscode-java-issue-2805/blob/4453856cb3df92026b7895929a46136a96d232b4/src/main/java/com/virtualvenue/model/widget/ContentWidget.java#L33)

## Example 2:
src/main/java/com/virtualvenue/model/widget/TextNotificationWidget.java line 15:

```java
@Data
@SuperBuilder(toBuilder = true)
//noArgs required for de-serializer and AllArgs for builder
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@XmlType(propOrder = {})//tell jaxb schemagen to generate xs:all instead of xs:sequence (we dont want ordering)
@XmlAccessorType(XmlAccessType.FIELD)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class TextNotificationWidget extends ContentWidget<TextNotificationWidgetConfiguration> {

}
```

[Permalink](https://github.com/zymotik/redhat-developer-vscode-java-issue-2805/blob/4453856cb3df92026b7895929a46136a96d232b4/src/main/java/com/virtualvenue/model/widget/TextNotificationWidget.java#L15)

### Example Code by Andrey Syagrovskiy

Also known as [@AndreySyagrovskiy](https://github.com/AndreySyagrovskiy), a skilled and experienced software engineer from Kyiv, Ukraine. Assisted by [@zymotik](https://github.com/zymotik), a team player with a last known location of Vila Nova de Milfontes, Portugal.
