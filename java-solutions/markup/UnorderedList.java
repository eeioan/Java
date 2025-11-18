package markup;

import java.util.List;

public class UnorderedList extends AbstractLists   {
    public UnorderedList(List<ListItem> items) {
        super(items);
    }

    @Override
    public void toMarkdown(StringBuilder sb) {
        listToMarkdown(sb, "* ");


    }

    @Override
    public void toTex(StringBuilder sb) {
        listToTex(sb, "itemize");
    }
}
