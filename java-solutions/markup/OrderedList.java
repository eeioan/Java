package markup;

import java.util.List;

public class OrderedList extends AbstractLists {

    public OrderedList(List<ListItem> items) {
        super(items);
    }

    @Override
    public void toMarkdown(StringBuilder sb) {
        int index = 1;
        for (ListItem item : items) {
            sb.append(index).append(". ");
            item.toMarkdown(sb);
            sb.append("\n");
            index++;
        }
    }

    @Override
    public void toTex(StringBuilder sb) {
        listToTex(sb, "enumerate");
    }
}



