<%@page import="com.amazonaws.a2s.AmazonA2S"%>
<%@page import="com.amazonaws.a2s.AmazonA2SClient"%>
<%@page import="com.amazonaws.a2s.model.AlternateVersion"%>
<%@page import="com.amazonaws.a2s.model.Item"%>
<%@page import="com.amazonaws.a2s.model.ItemSearchRequest"%>
<%@page import="com.amazonaws.a2s.model.ItemSearchResponse"%>
<%@page import="com.amazonaws.a2s.model.Items"%>
<%@page import="com.amazonaws.a2s.model.Image"%>

<%@page import="java.util.*"%>
<%@page import="com.softwaresmithy.acornweb.AcornWebQueryEngine"%>

<html>
	<head><title>BookTracker</title>
		<!-- NiftyCube libraries -->
		<script type="text/javascript" src="scripts/NiftyCube/niftycube.js"></script>
		<link rel="stylesheet" href="scripts/NiftyCube/niftyCorners.css">
		
		<style type="text/css">
			div.round{
				width: 60em;
				padding: 20px;
				margin-top:8px;
			    background:#E6E6E6;
				color:#000
			}
			.cover{
				//position: absolute;
			}
			.info{
				float: left;
				width: 100%;
				margin-top: -75px;
				height: 75px;
				//position: absolute;
			}
			.links{
				float:right;
				//position: absolute;
			}
			.rating{
				line-height: 4;
				float: left;
			}
			.bio{
				width: 490px;
				white-space: nowrap;
				padding-left: 76px;
				float: left;
			}
			.right{
				width:360px;
				float: right;
			}
			.title{
				font-family: sans-serif;
				font-size: 30px;
				font-weight: bold;
			}
			.tags{
				font-size: 80%;
				line-height: 2;
			}
		</style>
		<script type="text/javascript">
			window.onload=function(){
				Nifty("div.round","big");
			}
		</script>
	</head>
	<body>
		<form method="get" action="index.jsp">
			<input type="text" name="search">
			<input type="submit">
		</form>
	<%
	/************************************************************************
         * Access Key ID obtained from:
         * http://aws.amazon.com
         ***********************************************************************/
        String accessKeyId = "1JE5WNWJ29J99WBX06G2";
        String associateTag = "scumbkt19";

        /************************************************************************
         * Instantiate Client Implementation of Amazon A2S 
         ***********************************************************************/
        AmazonA2S service = new AmazonA2SClient(accessKeyId, associateTag);

         ItemSearchRequest searchRequest = new ItemSearchRequest();
        
         // @TODO: set searchRequest parameters here

         // invokeItemSearch(service, searchRequest);
         searchRequest.setSearchIndex("Books");
         
         java.util.List<String> responseGroup = new java.util.ArrayList();
         
         //Valid values for ResponseGroup include Accessories, AlternateVersions, BrowseNodes, Collections, 
         //EditorialReview, Images, ItemAttributes, ItemIds, Large, ListmaniaLists, Medium, 
         //MerchantItemAttributes, OfferFull, OfferListings, OfferSummary, Offers, PromotionDetails, 
         //PromotionSummary, PromotionalTag, RelatedItems, searchRequest, Reviews, SalesRank, SearchBins, 
         //SearchInside, Similarities, Small, Subjects, Tags, TagsSummary, Tracks, VariationMatrix, 
         //VariationMinimum, VariationOffers, VariationSummary, Variations.

         responseGroup.add("Small");
         responseGroup.add("AlternateVersions");
		 responseGroup.add("Images");
         responseGroup.add("Tags");
         responseGroup.add("Reviews");		 
         searchRequest.setResponseGroup(responseGroup);
         //searchRequest.setAuthor("Stephen R. Lawhead");
         
		 if(request.getParameter("search")!= null && !request.getParameter("search").equals("")){
			 searchRequest.setKeywords(request.getParameter("search"));
	         try{
	             AcornWebQueryEngine query = new AcornWebQueryEngine();
	             
	             ItemSearchResponse searchResponse = service.itemSearch(searchRequest);

	             java.util.List<Items> items = searchResponse.getItems();
	             List<Item> item = items.get(0).getItem();
	             for(int j=0;j<item.size();j++){
					 %>
					 <div class="round">
						<img class="cover" src="<%=item.get(j).getSmallImage().getURL() %>"></img>
						<div class="info">
							<div class="bio">
								<div class="title"><%=item.get(j).getItemAttributes().getTitle()%></div>
								<div class="author">by <%=item.get(j).getItemAttributes().getAuthor().get(0)%></div><!--Handle multiple authors!-->
								<div class="tags"><%= "apple, banana, cranberry, author, title"%></div>
							</div>
							<div class="right">
								<div class="rating">* * * * *</div>
								<div class="links">
									<div class="library">Reserve from Library</div>
									<div class="amazon">Buy at Amazon</div>
								</div>
							</div>
						</div>
					</div>
					<%
					 out.println("<br/>");
	                 java.util.List<String> isbns = new ArrayList();
	                 out.println(item.get(j).getItemAttributes().getTitle());
	                 isbns.add(item.get(j).getASIN());
	                 out.println(item.get(j).getASIN());

	                 }
					 
	                 //if(item.get(j).isSetAlternateVersions()){
	                 //   java.util.List<AlternateVersion> av = item.get(j).getAlternateVersions().getAlternateVersion();
	                 //   for(int k=0;k<av.size();k++){
	                 //       isbns.add(av.get(k).getASIN());
	                 //       out.println(av.get(k).getASIN()+": "+av.get(k).getTitle());
	                 //   }
	                 //}
	                 //out.println("at library: "+query.atLibrary(isbns));
	             //invokeItemSearch(service, searchRequest);
	         }catch(Exception e){
	             e.printStackTrace();
	         }
		 }
	%>
	</body>
</html>