# PoetryGraph
program that completes sentences of poetry with bridge words using graphs and weighted edges

Example 1 
  Poem Input:
  To explore strange new worlds
  To seek out new life and new civilizations
  
  GraphPoet will use this corpus to generate a word affinity graph where:
  • vertices are case-insensitive words, and
  • edge weights are in-order adjacency counts.
  
  
  
  Input:
  Seek to explore new and exciting synergies
  The poet will use the graph to generate a poem by inserting (where possible) a bridge word between each pair
  of input words given the poem input
  
  The resulting output:
  Seek to explore strange new life and exciting synergies


Example 2 

  We create a bigram model where we keep track of the weights of each word.
  
  Poem Input 1:
  To explore happy new worlds
  To seek out new life and new civilizations
  To explore strange new things
  To explore happy new things
  
  Input 2:
  Seek to explore new and exciting synergies
  
  • Notice there is two types of bridge words between ‘explore’ and ‘new’:
  Explore -> happy -> new (2) Appears twice in the paragraph above
  Explore -> strange -> new (1) Appears once in the paragraph above.
  As a result, there will be two edges between ‘explore’ and ‘new’
  
  ‘happy’ with weight 2
  ‘strange’ with weight 1
  
  Return an output with the highest weight (i.e. weight 2):
  
  Output:
  è Seek to explore happy new life and exciting synergies


