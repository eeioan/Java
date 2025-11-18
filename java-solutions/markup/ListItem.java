package markup;
import java.util.List;

    public class ListItem implements Markup {
        private final List<Lists> elements;
        public ListItem(List<Lists> elements) {
            this.elements=elements;
        }
        @Override
        public void toMarkdown(StringBuilder sb) {
            for  (Lists element : elements) {
                element.toMarkdown(sb);
            }
        }
        @Override
        public void toTex(StringBuilder sb) {
            sb.append("\\item ");
            for (Lists element : elements) {
                element.toTex(sb);

            }
        }
}