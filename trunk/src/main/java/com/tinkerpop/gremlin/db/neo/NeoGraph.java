package com.tinkerpop.gremlin.db.neo;

import com.tinkerpop.gremlin.model.Graph;
import com.tinkerpop.gremlin.model.Vertex;
import org.neo4j.api.core.NeoService;
import org.neo4j.api.core.EmbeddedNeo;
import org.neo4j.api.core.Node;
import org.neo4j.util.index.IndexService;
import org.neo4j.util.index.LuceneIndexService;

/**
 * @author Marko A. Rodriguez (http://markorodriguez.com)
 * @version 0.1
 */
public class NeoGraph implements Graph {

    private NeoService neo;
    private IndexService index;
    private String indexKey;

    public NeoGraph(String directory, String indexKey) {
        this.neo = new EmbeddedNeo(directory);
        this.index = new LuceneIndexService(neo);
    }

    public Vertex getVertex(Object id) {
        Node node = this.index.getSingleNode(this.indexKey, id);
        if(null != node) {
            return new NeoVertex(node);
        } else {
            return null;
        }
    }

    public void shutdown() {
        this.neo.shutdown();
        this.index.shutdown();
    }
}
