package markup;

import java.util.List;

public class UnorderedList extends AbstractLists {
    public UnorderedList(List<ListItem> items) {
        super(items);
    }

    @Override
    protected String listMark() {
        return "* ";
    }

    @Override
    protected String listTex() {
        return "itemize";
    }
}
