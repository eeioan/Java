package markup;
import java.util.List;

public class Paragraph implements Lists  {
    private final List<ListItemContent> elements;

    public Paragraph(List<ListItemContent> elements) {
        this.elements = elements;
    }

    @Override
    public void toMarkdown(StringBuilder sb) {
        for (ListItemContent element : elements) {

            element.toMarkdown(sb);
        }
    }

    @Override
    public void toTex(StringBuilder sb) {
        sb.append("\\par{}");
        for (ListItemContent element : elements) {
            element.toTex(sb);
        }
    }
}