package com.theshy;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.LongField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.surround.parser.QueryParser;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.FuzzyQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.NumericRangeQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.SortField.Type;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.WildcardQuery;
import org.apache.lucene.search.highlight.Formatter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.Scorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

public class MyLucenePractice {
    // 定义索引库的位置
    private static final String INDEX_PATH = "D:/develop/work/temp/index";

    @Test
    public void indexCreate() throws IOException {
        // 准备数据  数据封装在Document对象中
        Document doc = new Document();
        //数据有不同字段，字段类型也不同
        //LongField IntField DoubleField....
        // StringField(索引但是默认不分词，数据被当成一个词条), Term(词条)
        // TextField(索引并且根据索引写入器中定义的分词器来进行分词，数据被当做多个词条)
        LongField id = new LongField("id", 1L, Store.YES);
        doc.add(id);
//		new TextField("title", "谷歌地图之父跳槽FaceBook", Store.YES);
        // TextField：创建索引并提供分词，StringField创建索引但不分词
        TextField content = new TextField("content", "传智播客是个好地方，上海校区更加好", Store.YES);
        doc.add(content);

        // 创建目录对象，指定索引库的存放位置；FSDirectory文件系统；RAMDirectory内存
        Directory directory = FSDirectory.open(new File(INDEX_PATH));
        // 创建分词器对象
//		StandardAnalyzer analyzer = new StandardAnalyzer();
        Analyzer analyzer = new IKAnalyzer();
        // 创建索引写入器配置对象，第一个参数版本VerSion.LATEST,第一个参数分词器
        IndexWriterConfig conf = new IndexWriterConfig(Version.LATEST, analyzer);
        // 创建索引写入器
        IndexWriter indexWriter = new IndexWriter(directory , conf);
        // 向索引库写入文档对象
//		indexWriter.addDocument(doc);
        List<Document> docs = new ArrayList<Document>();
        docs.add(doc);
        //批量添加索引
        indexWriter.addDocuments(docs);
        // 提交
        indexWriter.commit();
        // 关闭
        indexWriter.close();
    }

    @Test
    public void indexCreates() throws IOException {

        // 创建目录对象，指定索引库的存放位置；FSDirectory文件系统；RAMDirectory内存
        Directory directory = FSDirectory.open(new File(INDEX_PATH));
        // 创建分词器对象
//		StandardAnalyzer analyzer = new StandardAnalyzer();
        Analyzer analyzer = new IKAnalyzer();
        // 创建索引写入器配置对象，第一个参数版本VerSion.LATEST,第一个参数分词器
        IndexWriterConfig conf = new IndexWriterConfig(Version.LATEST, analyzer);
        // 创建索引写入器
        IndexWriter indexWriter = new IndexWriter(directory , conf);
        // 向索引库写入文档对象
//		indexWriter.addDocument(doc);
        List<Document> docs = new ArrayList<Document>();
        for(Long i = 2L ;i < 30 ; i ++){
            Document doc = new Document();
            LongField id = new LongField("id", i, Store.YES);
            doc.add(id);
            StringField title = new StringField("title", "Itcast"+i, Store.YES);
            doc.add(title);
            TextField content = new TextField("content", "鸟哥的linux技术私房菜"+i, Store.YES);
            if(i==17L){
                //激励因子  改变激励因子可以改变得分
                content.setBoost(10);
            }

            doc.add(content);
            docs.add(doc);
        }

        //批量添加索引
        indexWriter.addDocuments(docs);
        // 提交
        indexWriter.commit();
        // 关闭
        indexWriter.close();
    }

    /**
     * 查询索引
     * @throws IOException
     * @throws ParseException
     */
    @Test
    public void searchIndex() throws IOException, ParseException{

        //查询解析器对象  构造参数：1.搜索的目标字段名称   2.使用何种分词器对搜索的参数进行分析
//		QueryParser queryParser = new QueryParser("content", new IKAnalyzer());
        //同时查询多字段的查询解析器
        MultiFieldQueryParser parser = new MultiFieldQueryParser(new String[]{"id","content"}, new IKAnalyzer());
        // 对搜索的参数进行解析  解析后得到Query对象
        Query query = parser.parse("linux");
        DirectoryReader reader = DirectoryReader.open(FSDirectory.open(new File(INDEX_PATH)));
        //创建索引查询对象
        IndexSearcher indexSearcher = new IndexSearcher(reader);
        //topDocs：排名前 n 的结果集
        TopDocs topDocs = indexSearcher.search(query, Integer.MAX_VALUE);
        //得分文档集合
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;

        for(ScoreDoc sd : scoreDocs){
            Integer docID = sd.doc;
            Document document = indexSearcher.doc(docID);
            System.out.println(sd.score);
            System.out.println("搜索到的结果集id = " + document.get("id"));
            System.out.println("搜索到的结果集title = " + document.get("title"));
            System.out.println("搜索到的结果集content = " + document.get("content"));
        }
        indexSearcher.getIndexReader().close();
    }



    /**
     * 抽取出公用的查询方法
     * @param query
     * @throws IOException
     * @throws ParseException
     */
    public void baseSearch(Query query) throws IOException, ParseException{

        DirectoryReader reader = DirectoryReader.open(FSDirectory.open(new File(INDEX_PATH)));
        //创建索引查询对象
        IndexSearcher indexSearcher = new IndexSearcher(reader);
        //topDocs：排名前 n 的结果集
        TopDocs topDocs = indexSearcher.search(query, Integer.MAX_VALUE);
        //得分文档集合
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;

        for(ScoreDoc sd : scoreDocs){
            Integer docID = sd.doc;
            Document document = indexSearcher.doc(docID);
            System.out.println("文档的得分："+sd.score);
            System.out.println("搜索到的结果集id = " + document.get("id"));
            System.out.println("搜索到的结果集title = " + document.get("title"));
            System.out.println("搜索到的结果集content = " + document.get("content"));
        }
        indexSearcher.getIndexReader().close();
    }
    /**
     * TermQuery : 词条搜索  单个词条的搜索，输入的内容会被当做一个完整的词条，不会再对搜索参数进行分词
     */
    @Test
    public void termQuery(){
//		Term term = new Term("查询的目标字段", "查询的参数");
        Term term = new Term("content", "linux");
        Query query = new TermQuery(term);
        try {
            baseSearch(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * WildcardQuery : 通配符搜索
     *  ?号代表单个字符，*号代表N个字符
     *
     */
    @Test
    public void wildcardQueryTest(){
        Term term = new Term("content", "?智播*");
        Query query = new WildcardQuery(term);
        try {
            baseSearch(query);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }


    /**
     * FuzzyQuery : 模糊查询
     *
     *  自动补齐或切换位置，至多两次机会
     *
     *  若大于2？
     */
    @Test
    public void fuzzyQueryTest(){
        Term term = new Term("content", "lnixu");
        Query query = new FuzzyQuery(term,2);
        try {
            baseSearch(query);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

    }


    /**
     * NumericRangeQuery:数值范围查询
     */
    @Test
    public void NumericRangeQueryTest(){
//		 NumericRangeQuery.newLongRange("搜索的目标字段", 起始范围, 结束范围, 是否包含最小, 是否包含最大);
        Query query = NumericRangeQuery.newLongRange("id", 5L, 30L, true, true);
        try {
            baseSearch(query);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * BooleanQuery : 组合搜索
     */
    @Test
    public void booleanQueryTest(){
        BooleanQuery query = new BooleanQuery();
        NumericRangeQuery<Long> rangeQuery = NumericRangeQuery.newLongRange("id", 5L, 30L, true, true);

        Term term = new Term("content", "传智播客");
        FuzzyQuery fuzzyQuery = new FuzzyQuery(term);

        // 交集： A结果集must(必须)  + B结果集must(必须) = A和B之间共同的部分
        // 并集： should + should = A和B的结果集合并
        //数值范围查询   返回5——29
        query.add(rangeQuery, Occur.SHOULD);
        //模糊查询  返回一条
        query.add(fuzzyQuery,Occur.SHOULD);

        try {
            baseSearch(query);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }


    /**
     * 修改索引      修改的原理：先删除根据条件查询的所有的结果，然后在添加一个新的文档对象Document
     * @throws IOException
     */
    @Test
    public void updateIndex() throws IOException{
        IndexWriterConfig conf = new IndexWriterConfig(Version.LATEST, new IKAnalyzer());
        IndexWriter indexWriter = new IndexWriter(FSDirectory.open(new File(INDEX_PATH)), conf);
        Term term = new Term("content", "传智播客");
        Document doc = new Document();
        LongField id = new LongField("id", 30L, Store.YES);
        doc.add(id);
        TextField title = new TextField("title", "谷歌地图之父跳槽FaceBook", Store.YES);
        doc.add(title);
        TextField content = new TextField("content", "黑马程序员是未来的社会栋梁", Store.YES);
        doc.add(content);
        // 根据指定的词条进行搜索，所有与词条匹配的内容会被指定的doc覆盖
        indexWriter.updateDocument(term, doc);
        indexWriter.commit();
        indexWriter.close();

    }

    /**
     * 删除索引
     * @throws IOException
     */
    @Test
    public void deleteIndex() throws IOException{
        IndexWriterConfig conf = new IndexWriterConfig(Version.LATEST, new IKAnalyzer());
        IndexWriter indexWriter = new IndexWriter(FSDirectory.open(new File(INDEX_PATH)), conf);
        //根据指定的词条进行删除
//		indexWriter.deleteDocuments(new Term("content","linux"));
        indexWriter.deleteAll();
        //删除所有
        indexWriter.commit();
        indexWriter.close();
    }


    /**
     * 高亮查询 ： 高亮就是搜索的参数（关键词高亮显示）
     * @throws InvalidTokenOffsetsException
     */
    @Test
    public void searchhighlighter() throws IOException, ParseException, InvalidTokenOffsetsException{

        //查询解析器对象  构造参数：1.搜索的目标字段名称   2.使用何种分词器对搜索的参数进行分析
//		QueryParser queryParser = new QueryParser("content", new IKAnalyzer());
        //同时查询多字段的查询解析器
        MultiFieldQueryParser parser = new MultiFieldQueryParser(new String[]{"id","content"}, new IKAnalyzer());
        // 对搜索的参数进行解析  解析后得到Query对象
        Query query = parser.parse("传智播客在哪里？");

        DirectoryReader reader = DirectoryReader.open(FSDirectory.open(new File(INDEX_PATH)));
        //创建索引查询对象
        IndexSearcher indexSearcher = new IndexSearcher(reader);

        Formatter formatter = new SimpleHTMLFormatter("<em color='red'>", "</em>");
        Scorer fragmentScorer = new QueryScorer(query);
        //创建高亮显示处理对象
        Highlighter highlighter = new Highlighter(formatter, fragmentScorer);

        //topDocs：排名前 n 的结果集
        TopDocs topDocs = indexSearcher.search(query, Integer.MAX_VALUE);
        //得分文档集合
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;

        for(ScoreDoc sd : scoreDocs){
            Integer docID = sd.doc;
            Document document = indexSearcher.doc(docID);
            System.out.println(sd.score);
            System.out.println("搜索到的结果集id = " + document.get("id"));
            System.out.println("搜索到的结果集title = " + document.get("title"));
            String content = document.get("content");
            //对结果集进行高亮处理
            String highlighterContent = highlighter.getBestFragment(new IKAnalyzer(), "content", content);
            System.out.println("搜索到的结果集content = " + highlighterContent);
        }
        indexSearcher.getIndexReader().close();
    }



    /**
     * 对搜索的结果集进行排序
     * @throws IOException
     * @throws ParseException
     */
    @Test
    public void sortSearch() throws IOException, ParseException{

        DirectoryReader reader = DirectoryReader.open(FSDirectory.open(new File(INDEX_PATH)));
        //创建索引查询对象
        IndexSearcher indexSearcher = new IndexSearcher(reader);

        org.apache.lucene.queryparser.classic.QueryParser parser = new org.apache.lucene.queryparser.classic.QueryParser("content", new IKAnalyzer());
        Query query = parser.parse("linux的优秀的操作系统");
        // SortField 指定排序字段
        //指定字段类型  指定使用的排序规则 false(升序)   ,true(降序)
        Sort sort = new Sort(new SortField("id", Type.LONG,true));

        //topDocs：排名前 n 的结果集
        TopDocs topDocs = indexSearcher.search(query, 30, sort);
        //得分文档集合
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;

        for(ScoreDoc sd : scoreDocs){
            Integer docID = sd.doc;
            Document document = indexSearcher.doc(docID);
            System.out.println("文档的得分："+sd.score);
            System.out.println("搜索到的结果集id = " + document.get("id"));
            System.out.println("搜索到的结果集title = " + document.get("title"));
            System.out.println("搜索到的结果集content = " + document.get("content"));
        }
        indexSearcher.getIndexReader().close();
    }

    /**
     * 分页查询 并根据id降序排列
     * @throws IOException
     * @throws ParseException
     */
    @Test
    public void pageSortSearch() throws IOException, ParseException{
        int pageNum = 3;
        int pageSize = 10;
        //起始位置
        int start = (pageNum -1)*pageSize;
        //结束位置
        int end = pageNum * pageSize;
        DirectoryReader reader = DirectoryReader.open(FSDirectory.open(new File(INDEX_PATH)));
        //创建索引查询对象
        IndexSearcher indexSearcher = new IndexSearcher(reader);

        org.apache.lucene.queryparser.classic.QueryParser parser = new org.apache.lucene.queryparser.classic.QueryParser("content", new IKAnalyzer());
        Query query = parser.parse("linux的优秀的操作系统");
        // SortField 指定排序字段  指定字段类型  指定使用的排序规则 false(升序)   ,true(降序)
        Sort sort = new Sort(new SortField("id", Type.LONG,true));

        //topDocs：排名前 n 的结果集
        TopDocs topDocs = indexSearcher.search(query, end, sort);
        //得分文档集合
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;

        for(int i=start ; i<scoreDocs.length;i++){
            ScoreDoc sd = scoreDocs[i];
            Integer docID = sd.doc;
            Document document = indexSearcher.doc(docID);
            System.out.println("文档的得分："+sd.score);
            System.out.println("搜索到的结果集id = " + document.get("id"));
            System.out.println("搜索到的结果集title = " + document.get("title"));
            System.out.println("搜索到的结果集content = " + document.get("content"));
        }
        indexSearcher.getIndexReader().close();
    }



}
