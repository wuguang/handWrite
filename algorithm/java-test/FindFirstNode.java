

public class FindFirstNode{
    public static void main(String[] args) {
		System.out.println("Hello , I am findFirstNode file~~");
	}

    public static Node getIntersectNode(Node head1,Node head2){
        if(head1 == null || head2 == null){
            return null;
        }

        Node loop1 = getLoopNode(head1);
        Node loop2 = getLoopNode(head2);
        //2个无环链表问题
        if(loop1 == null && loop2 == null){
            return noLoop(head1,head2);
        }
        //2个有环链表相交问题
        if(loop1 != null && loop2 !=null){
            return bothLoop(head1,loop1,head2,loop2);
        }
        //一个为空  一个不为空  不想交
        return null;
    }

    //2链表不相交，求，第一个交点
    public static Node noLoop02(Node head1,Node head2){
        if(head1 == null || head2 == null){
            return null;
        }
        Node cur1 = head1;
        Node cur2 = head2;

        //没有环
        while(cur1!=null){
            cur1 = cur1.next;
        }

        while(cur2!=null){
            cur2 = cur2.next;
        }

        //cur1, cur2指向各自的尾结点
        //

        while(true){
            cur1 = cur1.next;
            if(cur1 == null){
                // 从链表2开始
                cur1 = head2;
            }
            cur2 = cur2.next;
            if(cur2 == null){
                // 从链表1开始
                cur2 = head1;
            }

            if(cur1 == cur2 ){
                //找到相交点
                return cur1;
            }
        }


    }

    //2条链表是否 有相交， 如果相交，返回第一个交点
    public static Node noLoop(Node head1,Node head2){
        if(head1 == null || head2 == null){
            return null;
        }
        Node cur1 = head1;
        Node cur2 = head2;
        int n = 0;
        //计算2个链表的差值，及尾结点
        while(cur1 !=null){
            n++;
            cur1 = cur1.next;
        }
        //得到 链表1的长度，及尾结点cur1

        while(cur2 != null){
            n--;
            cur2 = cur2.next;
        }
        //得到 n1-n2的长度 n,及n2的尾结点 cur2


        if(n>0){
            //链表1长，
            cur1 = head1;
            cur2 = head2;
        }else{
            //链表2长，
            cur1 = head2;
            cur2 = head1;
        }
        n = Math.abs(n);

        while(n!=0){
            n--;
            cur1 = cur1.next;
        }

        //重新开始遍历，直到相等
        // 及第一个相交节点
        while(cur1 != cur2){
            cur1 = cur1.next;
            cur2 = cur2.next;
        }

        return cur1;
    }

    // loop1 是 head1 的 入环节点
    // loop2 是 head2 的 入环节点
    // 2个有环链表，返回第一个相交节点，如果不想交，返回null
    // loop1 是 head1 的入环节点
    // loop2 是 head2 的入环节点
    public static Node bothLoop(Node head1,Node loop1,Node head2,Node loop2){
        Node cur1 = null;
        Node cur2 = null;
        if(loop1 == loop2){
            cur1 = head1;
            cur2 = head2;
            int n = 0;
            while(cur1 != loop1){
                n++;
                cur1 = cur1.next;
            }
            while(cur2 != loop2){
                n--;
                cur2 = cur2.next;
            }
            // 计算出 head1 和 head2 的 长度差异后，
            // 从头开始
            cur1 = n>0?head1:head2;
            cur2 = cur1 == head1?head2:head1;
            n = Math.abs(n);
            // cur1 为长链表
            while(n!=0){
                n--;
                cur1 = cur1.next;
            }

            //相同长度时，一起走
            while(cur1 != cur2){
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            //直到相等
            //第一个相交点
            return  cur1;
        }else{
            //入环节点的下一个
            cur1 = loop1.next;
            while(cur1!=loop1){
                if(cur1 == loop2){
                    return loop1;
                }
                cur1 = cur1.next;
            }
            return null;
        }
    }

    public static Node getLoopNode(Node head){
        if(head == null || head.next == null || head.next.next == null){
            return null;
        }

        Node n1 = head.next; // slow
        Node n2 = head.next.next;
        while(n1 != n2){
            if(n2.next == null || n2.next.next == null){
                //不管谁先走到底，没有环
                return null;
            }
            n2 = n2.next.next;
            n1 = n1.next;
        }
        // 以上跳出循环，说明相交,相交点小于2圈
        // 继续找第一个相交点
        // 快指针，从头开始，慢指针保持不变,第一个相遇的地方即交点

        n2 = head;
        while(n1 != n2){
            n1 = n1.next;
            n2 = n2.next;
        }
        //直到相等，即第一个相交点
        return n1;
    }

    class Node{
        public Integer value = null;
        public Node next;
        /*
        Constructor(Integer val){
            value = val;
        }
        */
    }



}