package markup;

import java.util.List;

public abstract class Inline implements ListItemContent {
    protected List<ListItemContent> elements;

    public Inline(List<ListItemContent> elements) {
        this.elements = elements;
    }

    public void toMarkdown(StringBuilder sb) {
        sb.append(openMark());
        for (ListItemContent element : elements) {
            element.toMarkdown(sb);
        }
    }

    protected abstract String openMark();

    protected void elemToTex(StringBuilder sb) {
        for (ListItemContent element : elements) {
            element.toTex(sb);
        }
    }
}
