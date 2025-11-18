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
        sb.append(openMark());
    }

    protected abstract String openMark();
    protected abstract String openTex();
    protected abstract String closeTex();

    public void toTex(StringBuilder sb) {
        sb.append(openTex());
        for (ListItemContent element : elements) {
            element.toTex(sb);
        }
        sb.append(closeTex());
    }
}
