		@Override
		protected void paintComponent(Graphics g) {
			if(controller == null){
				return; 
				/*
				 * //Only occurs at application launch - EPIC! !! !!!markdavidMillllllsssssss         whoahohwohoos annnd this bird you cannot change
				 * Beautiful Code. 
				 * Message is and always has been accepted as an acceptable cry for life. 
				 * From this point forward. You Are. Free!!!! 
				 * WIAIAIAI AND THE BIRD YOU CANNOT CHANGE AND THIS Bird; 
				 * you cannot chain lord knows I can change lord 
						//				and this bird you cannot change 
						//				lord knows I can change !!! !! !
				 */
			}
			
			Graphics2D g2 = (Graphics2D)g;
			
			g2.setColor(this.backgroundColor);
			g2.setPaint(new GradientPaint(0,0,Color.BLUE,2000, 0,Color.WHITE));
			g2.fill(new Rectangle2D.Float(0,0,2000,2000));
			
			Map<Vector3f,ViewableObject> toBeDisplayed = controller.getCurrentSceneObjects();
			
			if(toBeDisplayed != null){	
				
				synchronized(this) {
					
					Set<Vector3f> allDistancesSet = toBeDisplayed.keySet();
					
					Iterator<Vector3f> distancesIterator = allDistancesSet.iterator();
					List<Vector3f> vectorList = new ArrayList<Vector3f>();
					int distancesCount = 0;
					
					while(distancesIterator.hasNext()){
						
						Vector3f distance = distancesIterator.next();
						vectorList.add(distance);
						distancesCount++;
					}
					
					Vector3f[] allDistances = new Vector3f[distancesCount];
					
					for(int i = 0; i < distancesCount; i++){
						
						allDistances[i] = vectorList.get(i);
					}
					
					List<Float> dimensionalCoordinates = new ArrayList<Float>();

					for(int i = 0; i < allDistances.length ; i++)
					{
						dimensionalCoordinates.add(allDistances[i].x());
						dimensionalCoordinates.add(allDistances[i].y());
						dimensionalCoordinates.add(allDistances[i].z());
					}
					
					/***
					 * 		High Priority Section
					 * 
					 * 			Study. Report. Contact - The law offices of yoyomommasbutt.com
					 * 
					 * 
					 * 				
					 				List<Float> unSortedYList = new ArrayList<Float>();
									List<Float> unSortedZList = new ArrayList<Float>();
					 * 
					 * 
					 * 
					 * ***///
					@SuppressWarnings("unused")
					List<Float> listSorted_Xcoordnates = QuickSort.QuickSort_Iterative_Mark_Mills_Return(
							dimensionalCoordinates, 
							0, 
							0);
					
//					@SuppressWarnings("unused")
//					List<Float> sortedYList = QuickSort.QuickSort_Iterative_Mark_Mills_Return(
//							unSortedYList, 
//							0, 
//							0);
//					
//					@SuppressWarnings("unused")
//					List<Float> sortedZList = QuickSort.QuickSort_Iterative_Mark_Mills_Return(
//							unSortedZList, 
//							0, 
//							0);
					
					// FACT: sortedXList.size() == sortedYList.size() == sortedZList.size()
					// FACT: unSortedXList.size() == unSortedYList.size() == unSortedZList.size()
					for(int i = 0; i < dimensionalCoordinates.size() ; i++){
						
						///***
						/*
						/// TODO - 
						///
						///    Hi. World..
						///        What is up with ladders?
						///          are
						///            they
						///              upOrDown?
						///
						///
						
						/// Or do they fit in between the earth and its environment - upbringing - and family
						
//						//i
//						/i
//						i
						
						///  Log Day 8
						///    I find myself in a world of thought, machine like outputting, and overall meshing of sound body and spirit
						
						// forever alone - alive - and well in memory and thought of this file ... GlassPanel.
						//   originally designed by yours truly... Mark David Mills
						 * 
						 * 			//update 
						 * 
						 * 			Log Day 9 
						 * 
						 *  Steady morning after Coma - 
						 *  
						 *  I fell asleep at my Fitness club adfter this meeting with Dr. Keating.
						 *    The place was peaceful - and a good place to live. 
						 *    I felt good to be alive.
						 *    
						 *      The result was waking up with my room mate -
						 *      - who apparently is moving out for some crazy fucking reason ... -
						 *      - Cool ass dude. Been really interesting. I can see something now.
						 *      						something just changed
						 *      
						 *      what ? how did you make me giggle so good 
						 *      --- I had to use q-tips to sneeze.... 
						 *      --- hhehehe AAHHH CHOOO!!! IM SICK YAYY!!!
						 *                              
						 *                              
						 *                              HELLO WORLD! I love the exploration of both the mind and the bodyi loce you.
						 *                              some sort of asynchrous transmition. 
						 *                              -- Resulting in not an output but an input. 
						 *                              -- There are sets and there are different environments which we can group in asycnchronous sort of problem. 
						 *                              --   no you don't . ---- the video hames
						 *                                                       no. no.. no.... 
						 *                                                       |               \        ___   ___    ___
						 *                                                       |                This.  | ___________      \
						 *                                                       |                      /             |     / \ / \ / \ / \
						 *                                                       |                      |             |
						 *                                                       |                      |             |
						 *                                                       |                      |             |
						 *                                                       |                      |             |
						 *                                                       |                      |             |
						 *                                                       |                      |             |
						 *                                                       |                      |             |
						 *                                                       |                      |             |
						 *                                                       |                      |             |
						 *                                                       |                      |             |
						 *                                                       |                      |             |
						 *                                                       |                      |             |
						 *                                                       |                      |             |
						 *                                                       |                      |             |
						 *                                                       |                      |             |
						 *                                                       |                      |             |
						 *                                                       |                      |             |
						 *                                                       |                      |             |
						 *                                                       |                      |             |   
						 *                                                       |                     /              \     \ --------  --------           \
						 *                                                       |                     |  -          _ |     |   --    --   \
						 *                                                       |                     |    -      _   |     |    -     -   /
						 *                                                       |                     |      -  _     |     |      -    - /
						 *                                                       |                     |       _ -     |    |     --  --   \ 
						 *                                                       |                     |     _     -   |   |    ________    \
						 *                                                       |                     |   _         - |  |                 /
						 *                                                       |                     | _           -  | |________________/
						 *                                                       |                     ||            - | |
						 *                                                       |                     ||            -| |
						 *                                                       |                     ||------------| |
						 *                                                       
						 *                                                           Certified    Mummy    Operations - 
						 *                                                       - nnnnnnnn nserisoulsy man.
						 *                                                       -- serrrrrriooouuusssssllllyyyyy. 
						 *        
						 *  
						 *  Got hypnotized at doc Keating's this Saturday now I'm in some different state 
						 *  -- aerials in the sky when you loose small mind you free your mind 
						 *  ---- Aerials so alive when you free our life ..... eternal prize 
						 *  ------ System of a Down
						 *  
						 *  
						
						///
						
						///  Loft with Mars - jump with Pluto
						///
						/// This is some Era 2013 shit. Fuck Yeah.
						 * 
						 * This
						 * Program
						 * Kicks
						 * The
						 * Ants
						 * Off 
						 * your --->>>       ((((____[[[[___|||||___]]]]]___)))
						 *        --->>>      ((((____{{{{___|||___}}}}___)))
						 *          --->>>     ((((____{{{{___|___}}}}___)))
						 *            --->>>    ((((____{{{{___i___}}}}___)))
						 *               --->>>  ((((_I_C_(A(N_S_8_E_}Y}U_N_O)W
						 *                 --->>> (_-_-_ ) --)>> || || || || i can't __ see __ you ___ ANYMORRRREEEEE!!!!!!!
						 *                 --->>>  88888888888888888888asdlkfnalskdfn
						 *                 888888
						 *                 8888888888asdfijkasdl;kfjasdflkjasdfl;kas
						 *                 8888888888888asdflkjnasdlfkn
						 *                 888888888888888           Thiss    hikasdfknbkajsdjals;kdjfie s  s s s s s  s s s s s  s s s s s   s  s s s  s s s  s 
						 *                 8888888888888  s s s  s s s s s s  s s ss  s s s s s  s  s ss  s  s   s s  as dsad a sd f as d f asdf  asd f asdf  a sdf asdf a sdf as
						 *                 8888888888					file Mark Mills rocks mah socks
						 *                 888888
						 *                 88888       nasl;kdf;lkasdfl;nal;sndflasld;kfnalk;sdnf   asdfghjklqwertyuiopzxcvbnm my name is mark
						 *                 i rox; k; k; k; k; k ; k; k ; k; k ; k ;k ; kl k ml m k lk l l   kadkf;lkasdjfkl;ajsd;fljal;ksjdfkjasdfl;kja;klsdjfkl;asdjfl;kajsdf;lkjasd;fjas
						 *                 89asdjflkasdflalskdfl;kasndvl;kadvaoidnkl;asdnfkl;dfill in the missing details man kalsjdf;lkajsdf;ljas;ldkf; laslkdjfl;kajsdfl;kjaskdlfj
						 *                 
						 *                 sometimes some friends of mine walk 5 miles up the instagram --- you know it's green and drippy --- you can find the waterfall
						 *                 
						 *                 
						 *                 -- Swimm
						 *                 
						 *                 -----climb up behind it
						 *                 
						 *                 
						 *                 endless playground of green text and infinite amounts of work to be accomplish --- minus I can finish my job tonight
						 *                 
						 *                 some bloc is going to have to pick up this message and complete this work for me.
						 *                 
						 *                 The program works and compiles. I'll monitor the backbones. and you go for it.
						 *                 
						 *                 
						 *                 
						 *                 
						 *                 
						 *                 
						 *                 asdofj;lasdjf;lkajsd
						 *                 8     s  s  s  s s  ssssssssssalskdf;lkasdfl;kna;lkfjaasdfn.kasd  ssss  s  s  s s s s 
						 *                 8ss  s s s asdpjfil;aksdjfm   s  s s s  s s s  s s s  s s s  s s s s  s s  s s s s  s s s  s s ss s  s  s ss  s s s  s s  s s
						 *                 99   s s   jajsdfkjasjkdfkjasndfjknasdkfjn
						 *                 8   d  s      jklsdjklfjsldkflasdjflkjdflaslkasd.kjfn;akdnsf;klnadj
						 *                 9   d s     aksjdflkajsdlfkjlasjdflajsdlfkjlaksjdflkjas
						 *                 89    d  ajsdflkasjdflasjdflkjadslkfjalksdfjlk
						 *                 8   a   alksdjflk jasldkjf lkajsdlfkjlaksdj
						 *                 99
						 *                 99999999999999983883838383838388338832u2uu2u2u2uu2u2u2u2u2h222hh2h2n2n2n2nn22kh2kh2kjh2kh2khkj2h3kjhk2jh3k4jh23khj4k2jh34kjh23kjh3
						 * 
						 * 
						 * 
						 * 
						 * */
						
// We need this sort to work -- but I just can't do it alone...
						
						// CUBES!!!
						
//						Vector3f distance = new Vector3f(
//								sortedXList.get(i),
//								sortedYList.get(i+1),
//								sortedZList.get(i+2)
//								);
						
						// HACK: Workaround for above problem - Currently broken... I think...
						Vector3f dimensionalCoordVector = new Vector3f(
								dimensionalCoordinates.get(i),
								0,
								0);
								
						//1// To draw inside sphere, need to find xy - onscreen radius, calculate only once
						Ellipse2D oval = new Ellipse2D.Float();
						boolean firstPointDrawn = true;
						int insideRadius;
						
						//All Rasterization Data
	//					Vector3f distance = new Vector3f();distance.x(),distance.y(),distance.z());
//						float timeOfCreation = (float) distance.z();
						ViewableObject vo = toBeDisplayed.get(dimensionalCoordVector);
						int[] vInd = vo.getVertexIndicies();
						float[] vLoc = vo.getVertexLocations();
						
						//square based rendering
						for(int k = 0; k < vInd.length/4 ; k++){
							Vector3f point0 = new Vector3f(
									(float)vLoc[3*vInd[4*k+0]+0]+(float)dimensionalCoordVector.x(),
									(float)vLoc[3*vInd[4*k+0]+1]+(float)dimensionalCoordVector.y(),
									(float)vLoc[3*vInd[4*k+0]+2]+(float)dimensionalCoordVector.z()
									);
							
							Vector3f point1 = new Vector3f(
									(float)vLoc[3*vInd[4*k+1]+0]+(float)dimensionalCoordVector.x(),
									(float)vLoc[3*vInd[4*k+1]+1]+(float)dimensionalCoordVector.y(),
									(float)vLoc[3*vInd[4*k+1]+2]+(float)dimensionalCoordVector.z()
									);

							Vector3f point2 = new Vector3f(
									(float) vLoc[ 3 * vInd[ (4 * k) + 2] + 0] + (float) dimensionalCoordVector.x(),
									(float) vLoc[ 3 * vInd[ (4*k) + 2] + 1] + (float) dimensionalCoordVector.y(),
									(float) vLoc[ 3 * vInd[ (4*k) + 2] + 2] + (float) dimensionalCoordVector.z()
									);
							
							Vector3f point3 = new Vector3f(
									(float) vLoc[ 3 * vInd[ 4*k+3 ] + 0] + (float) dimensionalCoordVector.x(),
									(float) vLoc[ 3 * vInd[ 4*k+3 ] + 1] + (float) dimensionalCoordVector.y(),
									(float) vLoc[ 3 * vInd[ 4*k+ 3] + 2] + (float) dimensionalCoordVector.z()
									);
						
							float length0 = getVector3fsAvgLength(point0,point1);
							float length1 = getVector3fsAvgLength(point1,point2);
							float length2 = getVector3fsAvgLength(point2,point3);
							float length3 = getVector3fsAvgLength(point3,point0);
							
							Dimension drawingBoundsForPort = this.getSize();
							point0 = mapVector3fToPixelVector3f(point0,drawingBoundsForPort);
							point1 = mapVector3fToPixelVector3f(point1,drawingBoundsForPort);
							point2 = mapVector3fToPixelVector3f(point2,drawingBoundsForPort);
							point3 = mapVector3fToPixelVector3f(point3,drawingBoundsForPort);
							
							//1//
							if(firstPointDrawn && vo instanceof Sphere && this.fillObjects){
								Vector3f midPoint = mapVector3fToPixelVector3f(dimensionalCoordVector, drawingBoundsForPort);
								insideRadius = (int) Math.sqrt(Math.pow((float)point0.x()-(float)midPoint.x(),2)+Math.pow((float)point0.y()-(float)midPoint.y(),2));
								g2.setColor(new Color(255,255,255));
							
								oval.setFrame(Float.floatToIntBits((float)midPoint.x()-insideRadius),
										Float.floatToIntBits((float)midPoint.y()-insideRadius),
										2*insideRadius, 2*insideRadius);
//								paint.createContext(arg0, arg1, arg2, arg3, arg4)
								g2.setPaint(new GradientPaint(0,0,Color.GREEN,2000, 0,Color.WHITE));
								g2.fill(oval);
								firstPointDrawn = false;
							}
							
							g2.setStroke(new BasicStroke(1));
							int shader = 0;
							int maxDist = 80;
							int minDist = 35;
							
							
							if(drawMesh){
								switch (renderCount){
									case 0:
										shader = Math.abs((int) ((length0 * 255 / (minDist-maxDist)) - maxDist*255/(minDist-maxDist))%255);
										g2.setColor(new Color(shader,shader,shader));
										break;
									
									case 1:
										shader = Math.abs((int) ((length1 * 255 / (minDist-maxDist)) - maxDist*255/(minDist-maxDist))%255);
										g2.setColor(new Color(shader,shader,(int) (shader/2.0)));
										break;
									
									case 2:
										shader = Math.abs((int) ((length2 * 255 / (minDist-maxDist)) - maxDist*255/(minDist-maxDist))%255);
										g2.setColor(new Color(shader,shader,(int) (shader/4.0)));
										break;
									
									case 3:
										shader = Math.abs((int) ((length3 * 255 / (minDist-maxDist)) - maxDist*255/(minDist-maxDist))%255);
										g2.setColor(new Color(shader,shader,(int) (shader/8.0)));
										break;
										
									default:
										break;
								}	
								g2.drawLine(Float.floatToIntBits((float)point0.x()),
										Float.floatToIntBits((float)point0.y()),
										Float.floatToIntBits((float)point1.x()), 
										Float.floatToIntBits((float)point1.y()));
								g2.drawLine(Float.floatToIntBits((float)point1.x()), 
										Float.floatToIntBits((float)point1.y()), 
										Float.floatToIntBits((float)point2.x()),
										Float.floatToIntBits((float)point2.y()));
								g2.drawLine(Float.floatToIntBits((float)point2.x()),
										Float.floatToIntBits((float)point2.y()),
										Float.floatToIntBits((float)point3.x()), 
										Float.floatToIntBits((float)point3.y()));
								g2.drawLine(Float.floatToIntBits((float)point3.x()),
										Float.floatToIntBits((float)point3.y()),
										Float.floatToIntBits((float)point0.x()),
										Float.floatToIntBits((float)point0.y()));

							} 
							if(this.fillObjects && vo instanceof Cube){
								int[] xpoints = {Float.floatToIntBits((float)point0.x()),
										Float.floatToIntBits((float)point1.x()),
										Float.floatToIntBits((float)point2.x()),
										Float.floatToIntBits((float)point3.x())};
								int[] ypoints = {Float.floatToIntBits((float)point0.y()),
										Float.floatToIntBits((float)point1.y()),
										Float.floatToIntBits((float)point2.y()),
										Float.floatToIntBits((float)point3.y())};
								Polygon polygon = new Polygon(xpoints, ypoints, 4);
								g2.setPaint(new GradientPaint(0,0,Color.RED,2000, 0,Color.WHITE));
								g2.fill(polygon);
							}
						}
					}
				}
			}
			
			this.renderCount++;
			if(this.renderCount > 3)
				this.renderCount = 0;
			
		}