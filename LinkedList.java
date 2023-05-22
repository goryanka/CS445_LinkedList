import java.io.*;
import java.util.*;

public class LinkedList
{
	private Node head;  // pointer to the front (first) element of the list

	public LinkedList()
	{
		head = null; // compiler does this anyway. just for emphasis
	}

	// LOAD LINKED LIST FORM INCOMING FILE	
	public LinkedList( String fileName, boolean orderedFlag )
	{
		head=null;
		try
		{
			BufferedReader infile = new BufferedReader( new FileReader( fileName ) );
			while ( infile.ready() )
			{
				if (orderedFlag)
					insertInOrder( infile.readLine() );  // WILL INSERT EACH ELEM INTO IT'S SORTED POSITION
				else
					insertAtTail( infile.readLine() );  // TACK EVERY NEWELEM ONTO END OF LIST. ORIGINAL ORDER PRESERVED
			}
			infile.close();
		}
		catch( Exception e )
		{
			System.out.println( "FATAL ERROR CAUGHT IN C'TOR: " + e );
			System.exit(0);
		}
	}

	//-------------------------------------------------------------

	public void insertAtFront( String data)
	{
		head = new Node(data,head);
	}

	public String toString()
	{
		String toString = "";
		for (Node curr = head; curr != null; curr = curr.next)
		{	toString += curr.data;		// WE ASSUME OURTYPE HAS toString() DEFINED
			if ( (curr.next) != null )
				toString += " ";
		}
		return toString;
	}

	// ######################## Y O U   W R I T E   T H E S E    M E T H O D S #####################

	public int size() 
	{
		
				int count = 0;
				if(head==null)
					return count;

				Node curr = head;
				while(curr != null){
					count++;
			     curr = curr.next;
			}
			 return count;
	}

	public boolean empty()
	{
		return size() == 0;
	}

	public boolean contains( String key )
	{
		return search(key) != null; 
	}

	public Node search( String key )
	{
		Node curr = head;
		
		while(curr != null)
		{
			if(key.equals(curr.data))
		         return curr;
		     curr = curr.next;
        }
        return curr;
	}

	public void insertAtTail(String  data) // TACK A NEW NODE (CABOOSE) ONTO THE END OF THE LIST
	{
		Node curr = null;

		if(head == null)
		{
		    insertAtFront(data);
		    return;
		}else{
			curr = head; 
		}
		while(curr != null && curr.next != null)
			curr = curr.next; 
		
	
        curr.next = new Node(data, null);
	}

	public void insertInOrder( String  data) // PLACE NODE IN LIST AT ITS SORTED ORDER POSTIOPN
	{
		Node curr = head;
		

		if(curr == null || data.compareTo(curr.data) < 0){
		 insertAtFront(data);
			return;
		}

		while(curr.next != null && data.compareTo(curr.next.data) >= 0)
		{
				
				curr = curr.next;
		}
  		
  		Node n = new Node(data, curr.next);
  		curr.next = n;

}
	
	public boolean remove( String key) // FIND/REMOVE 1st OCCURANCE OF A NODE CONTAINING KEY
	{
		Node curr = head; 
		Node temp = null; 

		if(curr != null && key.equals(curr.data)){
			head = curr.next;
			return true;
		}


		while(curr!=null && !key.equals(curr.data))
		{
			  temp = curr;
			  curr = curr.next;
		}
		if(curr == null) // does not founf key
			return false;
		temp.next = curr.next; // found key and remove it 
	

		 return true;

	}

	public boolean removeAtTail( )	// RETURNS TRUE IF THERE WAS NODE TO REMOVE
	{
		 if(head == null) 
		 	return false;

    	Node current = head;
    	Node prev = null;
    	
    	while(current.next !=null) {
       		 prev = current;
       		 current=current.next;
    }

    	if(prev == null) 
    		head = null;
    	else 
    		prev.next = null;

    	return false; 
	}

	public boolean removeAtFront() // RETURNS TRUE IF THERE WAS NODE TO REMOVE
	{
		
     
     if(head == null)
     	return false;

     if(head != null)
     {
     	head = head.next;

     }
    	return true;

	}
	
	public LinkedList union( LinkedList other )
	{

		LinkedList result = new LinkedList();
		
		
        Node curr = null; 
		for(curr = head; curr !=null; curr = curr.next )
		{ 
			if(!result.contains(curr.data))
			result.insertInOrder(curr.data);
		}


		for(curr = other.head; curr !=null; curr = curr.next )
		{ 
			if(!result.contains(curr.data))
			result.insertInOrder(curr.data);
		}
 		return result;
	}
	
	@SuppressWarnings("unchecked")
	public LinkedList inter( LinkedList other )
	{
		LinkedList result = new LinkedList();

		
		Node curr = null; 
        for(curr = head; curr !=null; curr = curr.next )
		{ 
			if(other.contains(curr.data))
			result.insertInOrder(curr.data);
		}
      
    
		return  result; 
	}


	public LinkedList diff( LinkedList other )
	{
		
       LinkedList result = new LinkedList();
       Node curr = null; 

       for(curr = head; curr !=null; curr = curr.next )
       	{ 
			if(!other.contains(curr.data))
			result.insertInOrder(curr.data);
		}


		return  result; 
	}

	public LinkedList xor( LinkedList other )
	{
		return  union(other).diff(inter(other));

	}

} //END LINKEDLIST CLASS

class Node
{ 
	String data;
	Node next;  
	Node ( String data, Node next )
	{ 
		this.data = data;
		this.next = next;
	}
} // END NODE CLASS