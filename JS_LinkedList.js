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
function createLinkedList() {
    let _head;
    let _tail;
    let _size = 0;

    return {
        insertHead: (data) => {
            let newNode = createNode(data, _head);
            if(_size === 0) {
                _head = _tail = newNode;
            } else {
                _head = newNode;
            }
            _size += 1;
        },
        insertTail: (data) => {
            let newNode = createNode(data);
            if(_size === 0) {
                _head = _tail = newNode;
            } else {
                _tail.setNext(newNode);
                _tail = newNode;
            }
            _size += 1;
        },
        getHead: () => _head,
        getTail: () => _tail,
        getSize: () => _size,
        reverse: () => {
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
        toString: () => {
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

    function createNode(data, next) {
        let _data = data;
        let _next = next;

        return {
            getData: () => _data,
            getNext: () => _next,
            setNext: (next) => _next = next
        };
    }
}

// let ll = createLinkedList();
// let nums = [...Array(20).keys()];
// for(num in nums) ll.insertTail(num);

// console.log(ll.toString());
// ll.reverse();
// console.log(ll.toString());
// ll.reverse();
// console.log(ll.toString());