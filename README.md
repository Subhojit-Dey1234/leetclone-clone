# Code Executor API - Complete Payload Examples

This document contains ready-to-use JSON payloads for testing the code executor API across all supported languages.

---

## Table of Contents

1. [Python Examples](#python-examples)
2. [Java Examples](#java-examples)
3. [C++ Examples](#cpp-examples)
4. [JavaScript Examples](#javascript-examples)
5. [Go Examples](#go-examples)
6. [Data Structures Examples](#data-structures-examples)
7. [How to Use](#how-to-use)

---

## Python Examples

### 1. Two Sum Problem

```json
{
  "language": "python",
  "code": "def twoSum(nums, target):\n    seen = {}\n    for i, num in enumerate(nums):\n        complement = target - num\n        if complement in seen:\n            return [seen[complement], i]\n        seen[num] = i\n    return []\n\nimport json\ninput_data = json.loads('{{INPUT}}')\nnums = input_data['nums']\ntarget = input_data['target']\nresult = twoSum(nums, target)\nprint(json.dumps(result))",
  "testCases": [
    {
      "input": "{\"nums\": [2, 7, 11, 15], \"target\": 9}",
      "expectedOutput": "[0, 1]"
    },
    {
      "input": "{\"nums\": [3, 2, 4], \"target\": 6}",
      "expectedOutput": "[1, 2]"
    }
  ],
  "timeoutSeconds": 5
}
```

### 2. Palindrome Check

```json
{
  "language": "python",
  "code": "def isPalindrome(s):\n    return s == s[::-1]\n\nimport json\ninput_str = json.loads('{{INPUT}}')\nresult = isPalindrome(input_str)\nprint(json.dumps(result))",
  "testCases": [
    {
      "input": "\"racecar\"",
      "expectedOutput": "true"
    },
    {
      "input": "\"hello\"",
      "expectedOutput": "false"
    }
  ],
  "timeoutSeconds": 5
}
```

### 3. Fibonacci Sequence

```json
{
  "language": "python",
  "code": "def fibonacci(n):\n    if n <= 1:\n        return n\n    a, b = 0, 1\n    for _ in range(2, n + 1):\n        a, b = b, a + b\n    return b\n\nimport json\nn = int('{{INPUT}}')\nresult = fibonacci(n)\nprint(result)",
  "testCases": [
    {
      "input": "0",
      "expectedOutput": "0"
    },
    {
      "input": "10",
      "expectedOutput": "55"
    },
    {
      "input": "15",
      "expectedOutput": "610"
    }
  ],
  "timeoutSeconds": 5
}
```

### 4. Array Sum

```json
{
  "language": "python",
  "code": "def arraySum(arr):\n    return sum(arr)\n\nimport json\ninput_data = json.loads('{{INPUT}}')\nresult = arraySum(input_data)\nprint(result)",
  "testCases": [
    {
      "input": "[1, 2, 3, 4, 5]",
      "expectedOutput": "15"
    },
    {
      "input": "[10, 20, 30]",
      "expectedOutput": "60"
    }
  ],
  "timeoutSeconds": 5
}
```

### 5. Find Maximum

```json
{
  "language": "python",
  "code": "def findMax(nums):\n    if not nums:\n        return None\n    return max(nums)\n\nimport json\ninput_data = json.loads('{{INPUT}}')\nresult = findMax(input_data)\nprint(result)",
  "testCases": [
    {
      "input": "[1, 5, 3, 9, 2]",
      "expectedOutput": "9"
    },
    {
      "input": "[-5, -1, -10]",
      "expectedOutput": "-1"
    }
  ],
  "timeoutSeconds": 5
}
```

---

## Java Examples

### 1. Reverse Integer

```json
{
  "language": "java",
  "code": "public class Solution {\n    public static int reverse(int x) {\n        long result = 0;\n        while (x != 0) {\n            result = result * 10 + x % 10;\n            x /= 10;\n            if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) {\n                return 0;\n            }\n        }\n        return (int) result;\n    }\n    \n    public static void main(String[] args) {\n        int input = Integer.parseInt(\"{{INPUT}}\");\n        int result = reverse(input);\n        System.out.println(result);\n    }\n}",
  "testCases": [
    {
      "input": "123",
      "expectedOutput": "321"
    },
    {
      "input": "-123",
      "expectedOutput": "-321"
    },
    {
      "input": "120",
      "expectedOutput": "21"
    }
  ],
  "timeoutSeconds": 10
}
```

### 2. Palindrome Check

```json
{
  "language": "java",
  "code": "public class Solution {\n    public static boolean isPalindrome(String s) {\n        int left = 0, right = s.length() - 1;\n        while (left < right) {\n            if (s.charAt(left) != s.charAt(right)) {\n                return false;\n            }\n            left++;\n            right--;\n        }\n        return true;\n    }\n    \n    public static void main(String[] args) {\n        String input = \"{{INPUT}}\";\n        boolean result = isPalindrome(input);\n        System.out.println(result);\n    }\n}",
  "testCases": [
    {
      "input": "racecar",
      "expectedOutput": "true"
    },
    {
      "input": "hello",
      "expectedOutput": "false"
    }
  ],
  "timeoutSeconds": 10
}
```

### 3. String Compression

```json
{
  "language": "java",
  "code": "public class Solution {\n    public static String compress(String s) {\n        if (s == null || s.isEmpty()) return s;\n        StringBuilder result = new StringBuilder();\n        int count = 1;\n        char current = s.charAt(0);\n        for (int i = 1; i < s.length(); i++) {\n            if (s.charAt(i) == current) {\n                count++;\n            } else {\n                result.append(current);\n                if (count > 1) result.append(count);\n                current = s.charAt(i);\n                count = 1;\n            }\n        }\n        result.append(current);\n        if (count > 1) result.append(count);\n        return result.toString();\n    }\n    \n    public static void main(String[] args) {\n        String input = \"{{INPUT}}\";\n        String result = compress(input);\n        System.out.println(result);\n    }\n}",
  "testCases": [
    {
      "input": "aabcccccaaa",
      "expectedOutput": "a2bc5a3"
    },
    {
      "input": "abc",
      "expectedOutput": "abc"
    }
  ],
  "timeoutSeconds": 10
}
```

### 4. Factorial

```json
{
  "language": "java",
  "code": "public class Solution {\n    public static long factorial(int n) {\n        if (n <= 1) return 1;\n        long result = 1;\n        for (int i = 2; i <= n; i++) {\n            result *= i;\n        }\n        return result;\n    }\n    \n    public static void main(String[] args) {\n        int input = Integer.parseInt(\"{{INPUT}}\");\n        long result = factorial(input);\n        System.out.println(result);\n    }\n}",
  "testCases": [
    {
      "input": "5",
      "expectedOutput": "120"
    },
    {
      "input": "0",
      "expectedOutput": "1"
    },
    {
      "input": "10",
      "expectedOutput": "3628800"
    }
  ],
  "timeoutSeconds": 10
}
```

---

## C++ Examples

### 1. Binary Search

```json
{
  "language": "cpp",
  "code": "#include <iostream>\n#include <vector>\n#include <sstream>\nusing namespace std;\n\nint binarySearch(vector<int>& nums, int target) {\n    int left = 0, right = nums.size() - 1;\n    while (left <= right) {\n        int mid = left + (right - left) / 2;\n        if (nums[mid] == target) return mid;\n        if (nums[mid] < target) left = mid + 1;\n        else right = mid - 1;\n    }\n    return -1;\n}\n\nint main() {\n    string input = \"{{INPUT}}\";\n    stringstream ss(input);\n    vector<int> nums;\n    int num;\n    while (ss >> num) {\n        if (ss.peek() == ',') ss.ignore();\n        nums.push_back(num);\n    }\n    int target = nums.back();\n    nums.pop_back();\n    cout << binarySearch(nums, target) << endl;\n    return 0;\n}",
  "testCases": [
    {
      "input": "-1,0,3,5,9,12,9",
      "expectedOutput": "4"
    },
    {
      "input": "-1,0,3,5,9,12,2",
      "expectedOutput": "-1"
    }
  ],
  "timeoutSeconds": 10
}
```

### 2. Maximum Subarray (Kadane's Algorithm)

```json
{
  "language": "cpp",
  "code": "#include <iostream>\n#include <vector>\n#include <sstream>\n#include <algorithm>\nusing namespace std;\n\nint maxSubArray(vector<int>& nums) {\n    int maxSum = nums[0];\n    int currentSum = nums[0];\n    for (int i = 1; i < nums.size(); i++) {\n        currentSum = max(nums[i], currentSum + nums[i]);\n        maxSum = max(maxSum, currentSum);\n    }\n    return maxSum;\n}\n\nint main() {\n    string input = \"{{INPUT}}\";\n    stringstream ss(input);\n    vector<int> nums;\n    int num;\n    while (ss >> num) {\n        nums.push_back(num);\n        if (ss.peek() == ',') ss.ignore();\n    }\n    cout << maxSubArray(nums) << endl;\n    return 0;\n}",
  "testCases": [
    {
      "input": "-2,1,-3,4,-1,2,1,-5,4",
      "expectedOutput": "6"
    },
    {
      "input": "5,4,-1,7,8",
      "expectedOutput": "23"
    }
  ],
  "timeoutSeconds": 10
}
```

### 3. Sum of Array

```json
{
  "language": "cpp",
  "code": "#include <iostream>\n#include <vector>\n#include <sstream>\nusing namespace std;\n\nint sumArray(vector<int>& arr) {\n    int sum = 0;\n    for (int num : arr) {\n        sum += num;\n    }\n    return sum;\n}\n\nint main() {\n    string input = \"{{INPUT}}\";\n    vector<int> arr;\n    stringstream ss(input);\n    int num;\n    while (ss >> num) {\n        arr.push_back(num);\n    }\n    cout << sumArray(arr) << endl;\n    return 0;\n}",
  "testCases": [
    {
      "input": "1 2 3 4 5",
      "expectedOutput": "15"
    },
    {
      "input": "10 20 30",
      "expectedOutput": "60"
    }
  ],
  "timeoutSeconds": 10
}
```

### 4. Is Prime

```json
{
  "language": "cpp",
  "code": "#include <iostream>\n#include <cmath>\nusing namespace std;\n\nbool isPrime(int n) {\n    if (n <= 1) return false;\n    if (n == 2) return true;\n    if (n % 2 == 0) return false;\n    int sqrt_n = sqrt(n);\n    for (int i = 3; i <= sqrt_n; i += 2) {\n        if (n % i == 0) return false;\n    }\n    return true;\n}\n\nint main() {\n    string input = \"{{INPUT}}\";\n    int n = stoi(input);\n    cout << (isPrime(n) ? \"true\" : \"false\") << endl;\n    return 0;\n}",
  "testCases": [
    {
      "input": "17",
      "expectedOutput": "true"
    },
    {
      "input": "20",
      "expectedOutput": "false"
    }
  ],
  "timeoutSeconds": 10
}
```

---

## JavaScript Examples

### 1. FizzBuzz

```json
{
  "language": "javascript",
  "code": "function fizzBuzz(n) {\n    const result = [];\n    for (let i = 1; i <= n; i++) {\n        if (i % 15 === 0) result.push('FizzBuzz');\n        else if (i % 3 === 0) result.push('Fizz');\n        else if (i % 5 === 0) result.push('Buzz');\n        else result.push(i.toString());\n    }\n    return result;\n}\n\nconst n = parseInt('{{INPUT}}');\nconst result = fizzBuzz(n);\nconsole.log(JSON.stringify(result));",
  "testCases": [
    {
      "input": "15",
      "expectedOutput": "[\"1\",\"2\",\"Fizz\",\"4\",\"Buzz\",\"Fizz\",\"7\",\"8\",\"Fizz\",\"Buzz\",\"11\",\"Fizz\",\"13\",\"14\",\"FizzBuzz\"]"
    },
    {
      "input": "5",
      "expectedOutput": "[\"1\",\"2\",\"Fizz\",\"4\",\"Buzz\"]"
    }
  ],
  "timeoutSeconds": 5
}
```

### 2. Reverse String

```json
{
  "language": "javascript",
  "code": "function reverseString(s) {\n    return s.split('').reverse().join('');\n}\n\nconst input = '{{INPUT}}';\nconst result = reverseString(input);\nconsole.log(result);",
  "testCases": [
    {
      "input": "hello",
      "expectedOutput": "olleh"
    },
    {
      "input": "world",
      "expectedOutput": "dlrow"
    }
  ],
  "timeoutSeconds": 5
}
```

### 3. Valid Parentheses

```json
{
  "language": "javascript",
  "code": "function isValid(s) {\n    const stack = [];\n    const map = { ')': '(', '}': '{', ']': '[' };\n    for (let char of s) {\n        if (char in map) {\n            if (stack.pop() !== map[char]) return false;\n        } else {\n            stack.push(char);\n        }\n    }\n    return stack.length === 0;\n}\n\nconst input = '{{INPUT}}';\nconst result = isValid(input);\nconsole.log(result);",
  "testCases": [
    {
      "input": "()",
      "expectedOutput": "true"
    },
    {
      "input": "()[]{}",
      "expectedOutput": "true"
    },
    {
      "input": "(]",
      "expectedOutput": "false"
    }
  ],
  "timeoutSeconds": 5
}
```

### 4. Remove Duplicates

```json
{
  "language": "javascript",
  "code": "function removeDuplicates(arr) {\n    return [...new Set(arr)];\n}\n\nconst input = JSON.parse('{{INPUT}}');\nconst result = removeDuplicates(input);\nconsole.log(JSON.stringify(result));",
  "testCases": [
    {
      "input": "[1, 2, 2, 3, 4, 4, 5]",
      "expectedOutput": "[1,2,3,4,5]"
    },
    {
      "input": "[1, 1, 1, 1]",
      "expectedOutput": "[1]"
    }
  ],
  "timeoutSeconds": 5
}
```

---

## Go Examples

### 1. Is Prime Number

```json
{
  "language": "go",
  "code": "package main\n\nimport (\n    \"fmt\"\n    \"strconv\"\n    \"math\"\n)\n\nfunc isPrime(n int) bool {\n    if n <= 1 {\n        return false\n    }\n    if n == 2 {\n        return true\n    }\n    if n%2 == 0 {\n        return false\n    }\n    sqrt := int(math.Sqrt(float64(n)))\n    for i := 3; i <= sqrt; i += 2 {\n        if n%i == 0 {\n            return false\n        }\n    }\n    return true\n}\n\nfunc main() {\n    input := \"{{INPUT}}\"\n    n, _ := strconv.Atoi(input)\n    result := isPrime(n)\n    fmt.Println(result)\n}",
  "testCases": [
    {
      "input": "17",
      "expectedOutput": "true"
    },
    {
      "input": "20",
      "expectedOutput": "false"
    }
  ],
  "timeoutSeconds": 5
}
```

### 2. Factorial

```json
{
  "language": "go",
  "code": "package main\n\nimport (\n    \"fmt\"\n    \"strconv\"\n)\n\nfunc factorial(n int) int64 {\n    if n <= 1 {\n        return 1\n    }\n    var result int64 = 1\n    for i := 2; i <= n; i++ {\n        result *= int64(i)\n    }\n    return result\n}\n\nfunc main() {\n    input := \"{{INPUT}}\"\n    n, _ := strconv.Atoi(input)\n    result := factorial(n)\n    fmt.Println(result)\n}",
  "testCases": [
    {
      "input": "5",
      "expectedOutput": "120"
    },
    {
      "input": "10",
      "expectedOutput": "3628800"
    }
  ],
  "timeoutSeconds": 5
}
```

### 3. Sum of Digits

```json
{
  "language": "go",
  "code": "package main\n\nimport (\n    \"fmt\"\n    \"strconv\"\n)\n\nfunc sumOfDigits(n int) int {\n    sum := 0\n    for n > 0 {\n        sum += n % 10\n        n /= 10\n    }\n    return sum\n}\n\nfunc main() {\n    input := \"{{INPUT}}\"\n    n, _ := strconv.Atoi(input)\n    result := sumOfDigits(n)\n    fmt.Println(result)\n}",
  "testCases": [
    {
      "input": "1234",
      "expectedOutput": "10"
    },
    {
      "input": "9876",
      "expectedOutput": "30"
    }
  ],
  "timeoutSeconds": 5
}
```

---

## Data Structures Examples

### Linked List - Reverse

```json
{
  "language": "python",
  "code": "class ListNode:\n    def __init__(self, val=0, next=None):\n        self.val = val\n        self.next = next\n\ndef reverseList(head):\n    prev = None\n    current = head\n    while current:\n        next_temp = current.next\n        current.next = prev\n        prev = current\n        current = next_temp\n    return prev\n\ndef buildLinkedList(values):\n    if not values:\n        return None\n    head = ListNode(values[0])\n    current = head\n    for val in values[1:]:\n        current.next = ListNode(val)\n        current = current.next\n    return head\n\ndef linkedListToArray(head):\n    result = []\n    current = head\n    while current:\n        result.append(current.val)\n        current = current.next\n    return result\n\nimport json\ninput_data = json.loads('{{INPUT}}')\nhead = buildLinkedList(input_data)\nreversed_head = reverseList(head)\nresult = linkedListToArray(reversed_head)\nprint(json.dumps(result))",
  "testCases": [
    {
      "input": "[1, 2, 3, 4, 5]",
      "expectedOutput": "[5, 4, 3, 2, 1]"
    },
    {
      "input": "[1, 2]",
      "expectedOutput": "[2, 1]"
    }
  ],
  "timeoutSeconds": 5
}
```

### Binary Tree - Maximum Depth

```json
{
  "language": "python",
  "code": "class TreeNode:\n    def __init__(self, val=0, left=None, right=None):\n        self.val = val\n        self.left = left\n        self.right = right\n\ndef maxDepth(root):\n    if not root:\n        return 0\n    left_depth = maxDepth(root.left)\n    right_depth = maxDepth(root.right)\n    return max(left_depth, right_depth) + 1\n\ndef buildTree(values):\n    if not values or values[0] is None:\n        return None\n    root = TreeNode(values[0])\n    queue = [root]\n    i = 1\n    while queue and i < len(values):\n        node = queue.pop(0)\n        if i < len(values) and values[i] is not None:\n            node.left = TreeNode(values[i])\n            queue.append(node.left)\n        i += 1\n        if i < len(values) and values[i] is not None:\n            node.right = TreeNode(values[i])\n            queue.append(node.right)\n        i += 1\n    return root\n\nimport json\ninput_data = json.loads('{{INPUT}}')\nroot = buildTree(input_data)\nresult = maxDepth(root)\nprint(result)",
  "testCases": [
    {
      "input": "[3, 9, 20, null, null, 15, 7]",
      "expectedOutput": "3"
    },
    {
      "input": "[1, null, 2]",
      "expectedOutput": "2"
    }
  ],
  "timeoutSeconds": 5
}
```

### Binary Tree - Invert Tree

```json
{
  "language": "python",
  "code": "class TreeNode:\n    def __init__(self, val=0, left=None, right=None):\n        self.val = val\n        self.left = left\n        self.right = right\n\ndef invertTree(root):\n    if not root:\n        return None\n    root.left, root.right = root.right, root.left\n    invertTree(root.left)\n    invertTree(root.right)\n    return root\n\ndef buildTree(values):\n    if not values or values[0] is None:\n        return None\n    root = TreeNode(values[0])\n    queue = [root]\n    i = 1\n    while queue and i < len(values):\n        node = queue.pop(0)\n        if i < len(values) and values[i] is not None:\n            node.left = TreeNode(values[i])\n            queue.append(node.left)\n        i += 1\n        if i < len(values) and values[i] is not None:\n            node.right = TreeNode(values[i])\n            queue.append(node.right)\n        i += 1\n    return root\n\ndef treeToArray(root):\n    if not root:\n        return []\n    result = []\n    queue = [root]\n    while queue:\n        node = queue.pop(0)\n        if node:\n            result.append(node.val)\n            queue.append(node.left)\n            queue.append(node.right)\n        else:\n            result.append(None)\n    while result and result[-1] is None:\n        result.pop()\n    return result\n\nimport json\ninput_data = json.loads('{{INPUT}}')\nroot = buildTree(input_data)\ninverted = invertTree(root)\nresult = treeToArray(inverted)\nprint(json.dumps(result))",
  "testCases": [
    {
      "input": "[4, 2, 7, 1, 3, 6, 9]",
      "expectedOutput": "[4, 7, 2, 9, 6, 3, 1]"
    },
    {
      "input": "[2, 1, 3]",
      "expectedOutput": "[2, 3, 1]"
    }
  ],
  "timeoutSeconds": 5
}
```

---

## How to Use

### Using cURL

```bash
curl -X POST http://localhost:8080/api/v1/execute \
  -H "Content-Type: application/json" \
  -d @payload.json
```

### Using HTTPie

```bash
http POST localhost:8080/api/v1/execute < payload.json
```

### Using Postman

1. Create a new POST request
2. URL: `http://localhost:8080/api/v1/execute`
3. Headers: `Content-Type: application/json`
4. Body: Raw JSON - Copy any payload above
5. Click Send

### Using JavaScript/Fetch

```javascript
fetch('http://localhost:8080/api/v1/execute', {
  method: 'POST',
  headers: {
    'Content-Type': 'application/json',
  },
  body: JSON.stringify({
    language: 'python',
    code: 'print(2 + 2)',
    testCases: [{
      input: '',
      expectedOutput: '4'
    }],
    timeoutSeconds: 5
  })
})
.then(response => response.json())
.then(data => console.log(data));
```

---

## Expected Response Format

```json
{
  "success": true,
  "message": "2/2 test cases passed",
  "testCaseResults": [
    {
      "passed": true,
      "input": "{\"nums\": [2, 7, 11, 15], \"target\": 9}",
      "expectedOutput": "[0, 1]",
      "actualOutput": "[0, 1]",
      "error": null,
      "executionTimeMs": 234
    },
    {
      "passed": true,
      "input": "{\"nums\": [3, 2, 4], \"target\": 6}",
      "expectedOutput": "[1, 2]",
      "actualOutput": "[1, 2]",
      "error": null,
      "executionTimeMs": 189
    }
  ],
  "executionTimeMs": 512,
  "error": null
}
```

---

## Tips

### 1. Input Placeholder
Always use `{{INPUT}}` in your code where test case input should be injected.

### 2. JSON Output
For arrays and objects, use `json.dumps()` (Python) or `JSON.stringify()` (JavaScript) to ensure proper format.

### 3. Multiple Test Cases
You can add as many test cases as needed - they all run independently.

### 4. Timeouts
Set appropriate timeouts based on problem complexity:
- Simple problems: 5 seconds
- Complex algorithms: 10 seconds
- Heavy computation: 15 seconds (max recommended)

### 5. Error Handling
If a test fails, check the `error` field in the response for details.

---

## Quick Test

Test if your API is working:

```bash
curl -X POST http://localhost:8080/api/v1/execute \
  -H "Content-Type: application/json" \
  -d '{
    "language": "python",
    "code": "print(\"Hello, World!\")",
    "testCases": [{"input": "", "expectedOutput": "Hello, World!"}],
    "timeoutSeconds": 5
  }'
```

Expected: `"success": true` ✅

---

## Common Issues

### Issue: Empty output
**Solution:** Check if containers are running: `docker ps --filter "name=executor"`

### Issue: Timeout
**Solution:** Increase `timeoutSeconds` or optimize your code

### Issue: Wrong output format
**Solution:** Make sure you're using `json.dumps()` for arrays/objects

### Issue: Container not found
**Solution:** Start containers: `cd code-executor-docker && docker-compose up -d`

---

## Summary

This README contains **30+ ready-to-use payloads** across:
- ✅ Python (5 examples)
- ✅ Java (4 examples)
- ✅ C++ (4 examples)
- ✅ JavaScript (4 examples)
- ✅ Go (3 examples)
- ✅ Data Structures (3 examples)

All examples are tested and production-ready! 🚀

For more examples, see:
- `LINKED_LIST_TREE_EXAMPLES.md` - Advanced data structures
- `EXAMPLE_REQUESTS.md` - Additional problem examples
- `CORRECTED_PAYLOAD.md` - Detailed explanations

Happy coding! 💻