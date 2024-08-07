#include <iostream>
using namespace std;

struct node
{
    int key;
    struct node *left, *right;
};

// Inorder traversal
void traverseInOrder(struct node *root)
{
    if (root == NULL)
    {
        return;
    }
    else
    {
        traverseInOrder(root->left);
        cout << root->key << " ";
        traverseInOrder(root->right);
    }
}

// Insert a node
struct node *insertNode(struct node *node, int key)
{
    if (node == NULL)
    {
        struct node *newNode = new struct node();
        node = newNode;
        node->key = key;
        node->left = node->right = NULL;
    }
    else if (node->key <= key)
    {
        node->right = insertNode(node->right, key);
    }
    else
    {
        node->left = insertNode(node->left, key);
    }
    return node;
}

// Deleting a node
struct node *deleteNode(struct node *root, int key)
{
    if (root == NULL)
    {
        return root;
    }
    if (root->key > key)
    {
        root->left = deleteNode(root->left, key);
    }
    else if (root->key < key)
    {
        root->right = deleteNode(root->right, key);
    }
    else
    {
        if (root->left && root->right == NULL)
        {
            delete root;
            root = NULL;
        }
        else if (root->left == NULL)
        {
            struct node *temp = root;
            root = root->right;
            delete temp;
        }
        else if (root->right == NULL)
        {
            struct node *temp = root;
            root = root->left;
            delete temp;
        }
        else
        {
            while (root->left != NULL)
            {
                root = root->left;
            }
            struct node *temp = root;
            root->key = temp->key;
            root->right = deleteNode(root->right, temp->key);
        }
    }
    return root;
}

// Driver code--
int main()
{
    struct node *root = NULL;

    int operation;
    int operand;
    cin >> operation;

    while (operation != -1)
    {
        switch (operation)
        {
        case 1: // insert
            cin >> operand;
            root = insertNode(root, operand);
            cin >> operation;
            break;
        case 2: // delete
            cin >> operand;
            root = deleteNode(root, operand);
            cin >> operation;
            break;
        default:
            cout << "Invalid Operator!\n";
            return 0;
        }
    }

    traverseInOrder(root);
}