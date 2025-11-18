package markup;

import java.util.List;

public abstract class AbstractLists implements Lists {
    protected final List<ListItem> items;

    protected abstract String listMark();

    protected abstract String listTex();

    public AbstractLists(List<ListItem> items) {
        this.items = items;
    }

    public void toMarkdown(StringBuilder sb) {
        for (ListItem item : items) {
            sb.append(listMark());
            item.toMarkdown(sb);
            sb.append("\n");
        }
    }

    public void toTex(StringBuilder sb) {
        sb.append("\\begin{").append(listTex()).append("}");
        for (ListItem item : items) {
            item.toTex(sb);
        }
        sb.append("\\end{").append(listTex()).append("}");
    }
}
