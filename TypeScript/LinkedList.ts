/**
 * Fully encapsulated Singly-Linked-List implementation using closures.
 * Instance fields are only accessible through functions returned by factory methods.
 * 
 * Supports:
 *      - Inserting elements at the head or tail of the list.
 *      - Iteration through the list (getHead() -> while(head != undefined) {head = head.getNext()})
 *      - Reversing the list (in O(n) time & O(1) space)
 *      - toString implementation, e.g. (0)->(1)->(2)->(3)->(4)
 */
interface LinkedList {
    insertHead: Function,
    insertTail: Function,
    getHead: Function,
    getTail: Function,
    getSize: Function,
    reverse: Function,
    toString: Function
}

interface ListNode {
    getData: Function,
    getNext: Function,
    setNext: Function
}

function createLinkedList() {
    let _head: ListNode;
    let _tail: ListNode;
    let _size: number = 0;

    return {
        insertHead: (data: any): void => {
            let newNode: ListNode = createNode(data, _head);
            if(_size === 0) {
                _head = _tail = newNode;
            } else {
                _head = newNode;
            }
            _size += 1;
        },
        insertTail: (data: any): void => {
            let newNode: ListNode = createNode(data);
            if(_size === 0) {
                _head = _tail = newNode;
            } else {
                _tail.setNext(newNode);
                _tail = newNode;
            }
            _size += 1;
        },
        getHead: (): ListNode => _head,
        getTail: (): ListNode => _tail,
        getSize: (): number => _size,
        reverse: (): void => {
            if(_head === undefined || _head.getNext() === undefined) return;
            let newHead = _head;
            while(_head.getNext() != undefined) {
                let move = _head.getNext();
                _head.setNext(_head.getNext().getNext());
                move.setNext(newHead);
                newHead = move;
            }
            _head = newHead;
        },
        toString: (): string => {
            if(_size === 0) return '()';
            let result = '';
            let node = _head;
            while(node.getNext() != undefined) {
                result += '('+node.getData()+')->';
                node = node.getNext();
            }
            result += '('+node.getData()+')';
            return result;
        }
    };

    function createNode(data: any, next?: ListNode) {
        let _data = data;
        let _next = next;

        return {
            getData: () => _data,
            getNext: () => _next,
            setNext: (next: ListNode) => _next = next
        };
    }
}

// let ll = createLinkedList();
// for(let i = 0; i <= 20; i++ ) ll.insertTail(i);

// let head = ll.getHead();
// while (head !== undefined) {
//     let num: number = head.getData();
//     console.log(`${num}: ${num % 2 === 0 ? 'Even' : 'Odd'}`);
//     head = head.getNext();
// }