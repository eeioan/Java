package markup;

import java.util.List;

public abstract class Inline implements ListItemContent {
    protected  List<ListItemContent> elements;
        public Inline(List<ListItemContent> elements) {
            this.elements = elements;
    }
    protected void elemToMarkdown(StringBuilder sb) {
        for (ListItemContent element : elements) {
            element.toMarkdown(sb);
        }
    }
    protected void elemToTex(StringBuilder sb) {
        for (ListItemContent     element : elements) {
            element.toTex(sb);
        }
    }
}
