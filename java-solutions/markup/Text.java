package markup;

class Text implements ListItemContent {
    private final String element;

    public Text(String element) {
        this.element = element;
    }

    @Override
    public void toMarkdown(StringBuilder sb) {
        sb.append(element);
    }

    @Override
    public void toTex(StringBuilder sb) {
        sb.append(element);
    }
}
