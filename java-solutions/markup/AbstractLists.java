package markup;

import java.util.List;

public abstract class AbstractLists implements Lists {
    protected final List<ListItem> items;

    public AbstractLists(List<ListItem> items) {
        this.items = items;
    }

    protected void listToMarkdown(StringBuilder sb, String prefix) {
        for (ListItem item : items) {
            sb.append(prefix);
            item.toMarkdown(sb);
            sb.append("\n");
        }
    }

    protected void listToTex(StringBuilder sb, String prefix) {
        sb.append("\\begin{").append(prefix).append("}");
        for (ListItem item : items) {
            item.toTex(sb);
        }
        sb.append("\\end{").append(prefix).append("}");
    }
}
