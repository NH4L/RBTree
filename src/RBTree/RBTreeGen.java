package RBTree;

/**
 * 红黑树实体 RBTree
 * @author lcy
 * @date 2019-12-27
 */
public class RBTreeGen {
    public TreeUnit _rootNode = null;
    private boolean search_result;

    public TreeUnit getRootNode() {
        return this._rootNode;
    }

    private void recursion_copyRootNode(TreeUnit currentUnit, TreeUnit copiedUnit) {
        if (currentUnit == null) {
            return;
        }
        if (copiedUnit == null) {
            copiedUnit = new TreeUnit();
        }
        copiedUnit.color = currentUnit.color;
        copiedUnit.indexNO = currentUnit.indexNO;
        copiedUnit.isRed = currentUnit.isRed;
        if (currentUnit._leftChild != null) {
            TreeUnit leftChild = new TreeUnit();
            leftChild._parent = copiedUnit;
            copiedUnit._leftChild = leftChild;
            recursion_copyRootNode(currentUnit._leftChild, leftChild);
        }
        if (currentUnit._rightChild != null) {
            TreeUnit rightChild = new TreeUnit();
            rightChild._parent = copiedUnit;
            copiedUnit._rightChild = rightChild;
            recursion_copyRootNode(currentUnit._rightChild, rightChild);
        }
    }

    public boolean containsKey(float indexNO) {
        this.search_result = false;


        if (this._rootNode == null) {
            return false;
        }
        recursion_search(this._rootNode, indexNO);
        return this.search_result;
    }

    public RBTreeGen(TreeUnit rootNode) {
        this.search_result = false;
        TreeUnit _rootNode1 = new TreeUnit();
        recursion_copyRootNode(rootNode, _rootNode1);
        this._rootNode = _rootNode1;
    }

    public RBTreeGen() {
        this.search_result = false;
    }

    private void recursion_search(TreeUnit _currentNode, float indexNO) {
        if (_currentNode == null) {
            return;
        }
        if (indexNO == _currentNode.indexNO) {
            this.search_result = true;
            return;
        }
        if (indexNO < _currentNode.indexNO) {
            if (_currentNode._leftChild == null) {
                return;
            }
            recursion_search(_currentNode._leftChild, indexNO);
            return;
        }
        if (indexNO > _currentNode.indexNO) {
            if (_currentNode._rightChild == null) {
                return;
            }
            recursion_search(_currentNode._rightChild, indexNO);
        }
    }

    private TreeUnit recursion_search_node(TreeUnit _currentNode, float indexNO) {
        if (_currentNode == null) {
            return null;
        }
        if (indexNO == _currentNode.indexNO) {
            return _currentNode;
        }
        if (indexNO < _currentNode.indexNO) {
            if (_currentNode._leftChild == null) {
                return null;
            }
            return recursion_search_node(_currentNode._leftChild, indexNO);
        }

        if (indexNO > _currentNode.indexNO) {
            if (_currentNode._rightChild == null) {
                return null;
            }
            return recursion_search_node(_currentNode._rightChild, indexNO);
        }
        return null;
    }


    private TreeUnit search_node(float indexNO) {
        return recursion_search_node(this._rootNode, indexNO);
    }

    public boolean insert(float indexNO) {
        if (this._rootNode == null) {
            this._rootNode = new TreeUnit();
            this._rootNode.indexNO = indexNO;
            this._rootNode.isRed = false;
            return true;
        }
        TreeUnit parentUnit = recursion_search_fitParentNode(this._rootNode, indexNO);
        if (parentUnit == null) {
            return false;
        }

        TreeUnit minNode = new TreeUnit();
        minNode.isRed = true;
        minNode.indexNO = indexNO;
        minNode._parent = parentUnit;
        if (indexNO < parentUnit.indexNO) {
            parentUnit._leftChild = minNode;

        } else if (indexNO > parentUnit.indexNO) {
            parentUnit._rightChild = minNode;
        } else {

            return false;
        }
        recursion_fixup_after_insert(minNode);
        return true;
    }


    private void recursion_fixup_after_insert(TreeUnit newNode) {
        if (newNode == null) {
            return;
        }

        if (newNode._parent == null) {
            newNode.isRed = false;

            return;
        }
        if (!newNode._parent.isRed) {
            return;
        }


        if (newNode._parent == null || newNode._parent._parent == null) {
            return;
        }
        TreeUnit currentNode = newNode;
        TreeUnit parentNode = newNode._parent;
        TreeUnit grandFatherNode = parentNode._parent;

        boolean isLeft_son = true;
        boolean isLeft_father = true;

        if (parentNode._rightChild != null && parentNode._rightChild.indexNO == currentNode.indexNO) {
            isLeft_son = false;
        }
        if (grandFatherNode._rightChild != null && grandFatherNode._rightChild.indexNO == parentNode.indexNO) {
            isLeft_father = false;
        }

        if (!isLeft_son && isLeft_father) {
            ___turnLeft(parentNode);
            ___turnRight(grandFatherNode);

            currentNode.isRed = true;
            parentNode.isRed = false;
            grandFatherNode.isRed = false;
            recursion_fixup_after_insert(currentNode);


            return;
        }

        if (isLeft_father && isLeft_son) {
            ___turnRight(grandFatherNode);

            currentNode.isRed = false;
            parentNode.isRed = true;
            grandFatherNode.isRed = false;
            recursion_fixup_after_insert(parentNode);


            return;
        }


        if (!isLeft_father && isLeft_son) {
            ___turnRight(parentNode);
            ___turnLeft(grandFatherNode);

            currentNode.isRed = true;
            parentNode.isRed = false;
            grandFatherNode.isRed = false;
            recursion_fixup_after_insert(currentNode);


            return;
        }

        if (!isLeft_father && !isLeft_son) {
            ___turnLeft(grandFatherNode);

            currentNode.isRed = false;
            parentNode.isRed = true;
            grandFatherNode.isRed = false;
            recursion_fixup_after_insert(parentNode);
            return;
        }
    }

    public boolean ___turnLeft(float indexNO) {
        TreeUnit node = search_node(indexNO);
        return ___turnLeft(node);
    }

    public boolean ___turnRight(float indexNO) {
        TreeUnit currentNode = search_node(indexNO);
        return ___turnRight(currentNode);
    }

    public boolean ___turnRight(TreeUnit currentNode) {
        if (currentNode == null) {
            return false;
        }
        if (currentNode._leftChild == null) {
            return false;
        }


        TreeUnit originLeftChild = currentNode._leftChild;
        if (currentNode._parent == null) {
            originLeftChild._parent = null;
            currentNode._leftChild = originLeftChild._rightChild;
            originLeftChild._rightChild = currentNode;

            if (currentNode._leftChild != null) {
                currentNode._leftChild._parent = currentNode;
            }

            currentNode._parent = originLeftChild;
            this._rootNode = originLeftChild;
            return true;
        }


        boolean isLeft = true;
        if (currentNode._parent._rightChild != null && currentNode._parent._rightChild.indexNO == currentNode.indexNO) {
            isLeft = false;
        }
        if (isLeft) {
            currentNode._parent._leftChild = originLeftChild;
        } else {

            currentNode._parent._rightChild = originLeftChild;
        }
        originLeftChild._parent = currentNode._parent;
        currentNode._leftChild = originLeftChild._rightChild;
        if (currentNode._leftChild != null) {
            currentNode._leftChild._parent = currentNode;
        }
        originLeftChild._rightChild = currentNode;
        currentNode._parent = originLeftChild;
        return true;
    }

    public boolean ___turnLeft(TreeUnit currentNode) {
        if (currentNode == null) {
            return false;
        }
        if (currentNode._rightChild == null) {
            return false;
        }


        TreeUnit originRightChild = currentNode._rightChild;
        if (currentNode._parent == null) {
            originRightChild._parent = null;
            currentNode._rightChild = originRightChild._leftChild;
            originRightChild._leftChild = currentNode;
            if (currentNode._rightChild != null) {
                currentNode._rightChild._parent = currentNode;
            }
            currentNode._parent = originRightChild;
            this._rootNode = originRightChild;
            return true;
        }


        boolean isLeft = true;
        if (currentNode._parent._rightChild != null && currentNode._parent._rightChild.indexNO == currentNode.indexNO) {
            isLeft = false;
        }
        if (isLeft) {
            currentNode._parent._leftChild = originRightChild;
        } else {

            currentNode._parent._rightChild = originRightChild;
        }
        originRightChild._parent = currentNode._parent;
        currentNode._rightChild = originRightChild._leftChild;
        if (currentNode._rightChild != null) {
            currentNode._rightChild._parent = currentNode;
        }
        originRightChild._leftChild = currentNode;
        currentNode._parent = originRightChild;
        return true;
    }


    public boolean ___insert(float indexNO) {
        if (this._rootNode == null) {
            this._rootNode = new TreeUnit();
            this._rootNode.indexNO = indexNO;
            this._rootNode.isRed = false;
            return true;
        }
        TreeUnit parentUnit = recursion_search_fitParentNode(this._rootNode, indexNO);
        if (parentUnit == null) {
            return false;
        }

        TreeUnit minNode = new TreeUnit();
        minNode.isRed = true;
        minNode.indexNO = indexNO;
        minNode._parent = parentUnit;
        if (indexNO < parentUnit.indexNO) {
            parentUnit._leftChild = minNode;

        } else if (indexNO > parentUnit.indexNO) {
            parentUnit._rightChild = minNode;
        } else {

            return false;
        }
        return false;
    }

    public void ___fillRed(float indexNO) {
        TreeUnit node = search_node(indexNO);
        if (node != null) {
            node.isRed = true;
            node.color = "red";
        }
    }

    public void ___fillBlack(float indexNO) {
        TreeUnit node = search_node(indexNO);
        if (node != null) {
            node.isRed = false;
            node.color = "black";
        }
    }

    private TreeUnit recursion_search_fitParentNode(TreeUnit _currentUnit, float indexNO) {
        if (_currentUnit == null) {
            return null;
        }
        if (indexNO < _currentUnit.indexNO) {
            if (_currentUnit._leftChild == null) {
                return _currentUnit;
            }

            return recursion_search_fitParentNode(_currentUnit._leftChild, indexNO);
        }

        if (indexNO > _currentUnit.indexNO) {
            if (_currentUnit._rightChild == null) {
                return _currentUnit;
            }

            return recursion_search_fitParentNode(_currentUnit._rightChild, indexNO);
        }

        return null;
    }

    public boolean delete(float indexNO) {
        TreeUnit originNode = search_node(indexNO);
        TreeUnit rec_node = null;
        TreeUnit nilNode = new TreeUnit();
        nilNode.isNIL = true;
        nilNode.isRed = false;
        if (originNode == null) {
            return false;
        }

        TreeUnit realDeletedNode = originNode;
        if (originNode._rightChild == null) {
            realDeletedNode = originNode;
        } else {

            realDeletedNode = recursion_search_real_deletedNode(originNode._rightChild);
        }

        if (realDeletedNode.indexNO == originNode.indexNO) {
            TreeUnit parentNode = originNode._parent;
            if (parentNode == null) {

                if (originNode._leftChild == null) {
                    this._rootNode = null;
                    rec_node = null;
                    return true;
                }
                this._rootNode = originNode._leftChild;
                this._rootNode._parent = null;
                this._rootNode.isRed = false;
                return true;
            }

            if (parentNode != null) {
                boolean isLeft = false;
                if (parentNode._leftChild != null && parentNode._leftChild.indexNO == originNode.indexNO) {
                    isLeft = true;
                }
                if (originNode.isRed) {
                    if (isLeft) {
                        parentNode._leftChild = null;
                    } else {

                        parentNode._rightChild = null;
                    }
                    return true;
                }
                boolean hasLeftChild = false;
                if (originNode._leftChild != null) {
                    hasLeftChild = true;
                }

                if (isLeft) {
                    parentNode._leftChild = originNode._leftChild;
                    if (originNode._leftChild != null) {
                        originNode._leftChild._parent = parentNode;
                    }
                } else if (!isLeft) {
                    parentNode._rightChild = originNode._leftChild;
                    if (originNode._leftChild != null) {
                        originNode._leftChild._parent = parentNode;
                    }
                }
                if (hasLeftChild) {
                    if (isLeft) {
                        recursion_fixup_afterDeletion(parentNode._leftChild);
                    } else {

                        recursion_fixup_afterDeletion(parentNode._rightChild);
                    }


                    return true;
                }
                nilNode._parent = parentNode;
                if (isLeft) {
                    parentNode._leftChild = nilNode;
                    recursion_fixup_afterDeletion(parentNode._leftChild);
                } else {

                    parentNode._rightChild = nilNode;
                    recursion_fixup_afterDeletion(parentNode._rightChild);
                }
                delNILNode(nilNode);
                return true;
            }
        } else {
            originNode.indexNO = realDeletedNode.indexNO;
            TreeUnit parentNode = realDeletedNode._parent;
            boolean isLeft = true;
            if (parentNode._rightChild != null && parentNode._rightChild.indexNO == realDeletedNode.indexNO) {
                isLeft = false;
            }

            if (realDeletedNode.isRed) {
                if (isLeft) {
                    parentNode._leftChild = null;
                    return true;
                }

                parentNode._rightChild = null;
                return true;
            }

            if (!realDeletedNode.isRed && realDeletedNode._rightChild != null && !realDeletedNode._rightChild.isNIL) {
                if (isLeft) {
                    parentNode._leftChild = realDeletedNode._rightChild;
                    realDeletedNode._rightChild._parent = parentNode;
                    recursion_fixup_afterDeletion(realDeletedNode._leftChild);
                    return true;
                }

                System.out.println("【当真实节点为黑色并且拥有红色右子节点时，删除真实节点，将右子节点提升到同样位置，右子节点参与到位置调整】");
                parentNode._rightChild = realDeletedNode._rightChild;
                realDeletedNode._rightChild._parent = parentNode;
                recursion_fixup_afterDeletion(parentNode._rightChild);
                return true;
            }


            if (!realDeletedNode.isRed && (realDeletedNode._rightChild == null || (realDeletedNode._rightChild != null && realDeletedNode._rightChild.isNIL))) {

                nilNode._parent = parentNode;
                if (isLeft) {
                    parentNode._leftChild = nilNode;
                } else {

                    parentNode._rightChild = nilNode;
                }


                recursion_fixup_afterDeletion(nilNode);
                delNILNode(nilNode);
                return true;
            }
        }

        return true;
    }


    private void delNILNode(TreeUnit nilNode) {
        if (nilNode == null) {
            return;
        }
        if (nilNode._parent == null) {
            return;
        }
        TreeUnit parentNode = nilNode._parent;
        if (parentNode._leftChild != null && parentNode._leftChild.isNIL) {
            parentNode._leftChild = null;
        }
        if (parentNode._rightChild != null && parentNode._rightChild.isNIL)
            parentNode._rightChild = null;
    }

    private TreeUnit recursion_search_real_deletedNode(TreeUnit currentUnit) {
        if (currentUnit == null) {
            return null;
        }
        if (currentUnit._rightChild == null && currentUnit._leftChild == null) {
            return currentUnit;
        }

        if (currentUnit._leftChild != null) {
            return recursion_search_real_deletedNode(currentUnit._leftChild);
        }

        return currentUnit;
    }


    private void recursion_fixup_afterDeletion(TreeUnit currentUnit) {
        if (currentUnit == null) {
            return;
        }
        if (currentUnit._parent == null) {
            currentUnit.isRed = false;
            return;
        }
        if (currentUnit.isRed) {
            currentUnit.isRed = false;


            return;
        }


        boolean isLeft = true;

        if (currentUnit.isNIL) {
            if (currentUnit._parent._leftChild.isNIL) {
                isLeft = true;
            } else {

                isLeft = false;

            }

        } else if (currentUnit._parent._leftChild.indexNO == currentUnit.indexNO) {
            isLeft = true;
        } else {

            isLeft = false;
        }

        TreeUnit grandFatherNode = null;
        TreeUnit parentNode = currentUnit._parent;
        float parentIndexNO = parentNode.indexNO;
        TreeUnit brotherNode = null;
        TreeUnit nearNePhewNode = null;
        TreeUnit farNephewNode = null;

        grandFatherNode = parentNode._parent;
        boolean isGrandFatherLeft = true;
        if (grandFatherNode != null &&
                grandFatherNode._rightChild != null && grandFatherNode._rightChild.indexNO == parentIndexNO) {
            isGrandFatherLeft = false;
        }


        if (parentNode != null) {
            grandFatherNode = parentNode._parent;
        }
        if (isLeft) {
            brotherNode = currentUnit._parent._rightChild;
            nearNePhewNode = brotherNode._leftChild;
            farNephewNode = brotherNode._rightChild;
        } else {

            brotherNode = currentUnit._parent._leftChild;
            nearNePhewNode = brotherNode._rightChild;
            farNephewNode = brotherNode._leftChild;
        }


        boolean isCaseB = false;

        if (!brotherNode.isRed) {
            if ((nearNePhewNode != null && nearNePhewNode.isRed) || (farNephewNode != null && farNephewNode.isRed)) {
                isCaseB = false;
            } else {

                isCaseB = true;
            }
        }
        if (isCaseB) {

            brotherNode.isRed = true;
            recursion_fixup_afterDeletion(parentNode);


            return;
        }

        boolean isCaseC = false;
        if (brotherNode.isRed) {
            isCaseC = true;
        }
        if (isCaseC) {
            if (isLeft) {

                TreeUnit brotherLeftChild = brotherNode._leftChild;

                brotherNode._leftChild = parentNode;
                parentNode._parent = brotherNode;

                parentNode._rightChild = brotherLeftChild;
                if (brotherLeftChild != null) {
                    brotherLeftChild._parent = parentNode;
                }

                parentNode.isRed = true;
                brotherNode.isRed = false;
                brotherNode._parent = grandFatherNode;
                if (grandFatherNode != null) {
                    if (isGrandFatherLeft) {
                        grandFatherNode._leftChild = brotherNode;
                    } else {

                        grandFatherNode._rightChild = brotherNode;
                    }
                } else {

                    this._rootNode = brotherNode;
                }
                recursion_fixup_afterDeletion(currentUnit);


                return;
            }


            TreeUnit brotherRightChild = brotherNode._rightChild;

            brotherNode._rightChild = parentNode;
            parentNode._parent = brotherNode;

            parentNode._leftChild = brotherRightChild;
            if (brotherRightChild != null) {
                brotherRightChild._parent = parentNode;
            }
            brotherNode.isRed = false;
            parentNode.isRed = true;
            brotherNode._parent = grandFatherNode;
            if (grandFatherNode != null) {
                if (isGrandFatherLeft) {
                    grandFatherNode._leftChild = brotherNode;
                } else {

                    grandFatherNode._rightChild = brotherNode;
                }
            } else {

                this._rootNode = brotherNode;
            }

            recursion_fixup_afterDeletion(currentUnit);
            return;
        }

        boolean isCaseD = false;
        boolean isCaseD_i = false;
        boolean isCaseD_ii = false;
        if (!brotherNode.isRed) {
            if (nearNePhewNode != null && nearNePhewNode.isRed) {
                isCaseD = true;

            } else if (farNephewNode != null && farNephewNode.isRed) {
                isCaseD = true;
            }
        }
        if (isCaseD) {
            if (farNephewNode != null && farNephewNode.isRed) {
                isCaseD_ii = true;
            } else if (nearNePhewNode != null && nearNePhewNode.isRed) {
                isCaseD_i = true;
            }
        }


        boolean partRootIsRed = false;

        if (isCaseD) {
            partRootIsRed = brotherNode._parent.isRed;
        }


        if (isCaseD_i) {
            TreeUnit tmpLeftChild = nearNePhewNode._leftChild;
            TreeUnit tmpRightChild = nearNePhewNode._rightChild;


            if (isLeft) {


                nearNePhewNode._leftChild = parentNode;
                parentNode._parent = nearNePhewNode;
                nearNePhewNode._rightChild = brotherNode;
                brotherNode._parent = nearNePhewNode;
                parentNode._rightChild = tmpLeftChild;
                if (tmpLeftChild != null) {
                    tmpLeftChild._parent = parentNode;
                }
                brotherNode._leftChild = tmpRightChild;
                if (tmpRightChild != null) {
                    tmpRightChild._parent = brotherNode;
                }
                parentNode.isRed = partRootIsRed;
                nearNePhewNode.isRed = false;
                nearNePhewNode._parent = grandFatherNode;
                if (grandFatherNode != null) {
                    if (isGrandFatherLeft) {
                        grandFatherNode._leftChild = nearNePhewNode;
                    } else {

                        grandFatherNode._rightChild = nearNePhewNode;
                    }
                }

                if (nearNePhewNode._parent == null) {
                    this._rootNode = nearNePhewNode;
                }


                return;
            }

            nearNePhewNode._leftChild = brotherNode;
            brotherNode._parent = nearNePhewNode;
            nearNePhewNode._rightChild = parentNode;
            parentNode._parent = nearNePhewNode;
            brotherNode._rightChild = tmpLeftChild;
            if (tmpLeftChild != null) {
                tmpLeftChild._parent = brotherNode;
            }
            parentNode._leftChild = tmpRightChild;
            if (tmpRightChild != null) {
                tmpRightChild._parent = parentNode;
            }
            nearNePhewNode._parent = grandFatherNode;
            parentNode.isRed = false;
            nearNePhewNode.isRed = partRootIsRed;
            if (grandFatherNode != null) {
                if (isGrandFatherLeft) {
                    grandFatherNode._leftChild = nearNePhewNode;
                } else {

                    grandFatherNode._rightChild = nearNePhewNode;
                }
            }
            if (nearNePhewNode._parent == null) {
                this._rootNode = nearNePhewNode;
            }


            return;
        }

        if (isCaseD_ii) {
            if (isLeft) {


                TreeUnit tmpChild = brotherNode._leftChild;
                brotherNode._leftChild = parentNode;
                parentNode._parent = brotherNode;
                parentNode._rightChild = tmpChild;
                if (tmpChild != null) {
                    tmpChild._parent = parentNode;
                }
                brotherNode._parent = grandFatherNode;
                if (grandFatherNode != null) {
                    if (isGrandFatherLeft) {
                        grandFatherNode._leftChild = brotherNode;
                    } else {

                        grandFatherNode._rightChild = brotherNode;
                    }
                }

                brotherNode.isRed = partRootIsRed;
                parentNode.isRed = false;
                farNephewNode.isRed = false;
                if (brotherNode._parent == null) {
                    this._rootNode = brotherNode;
                }


                return;
            }


            TreeUnit tmpChild = brotherNode._rightChild;
            brotherNode._rightChild = parentNode;
            parentNode._parent = brotherNode;
            parentNode._leftChild = tmpChild;
            if (tmpChild != null) {
                tmpChild._parent = parentNode;
            }
            parentNode.isRed = false;
            farNephewNode.isRed = false;
            brotherNode._parent = grandFatherNode;
            brotherNode.isRed = partRootIsRed;
            if (grandFatherNode != null) {
                if (isGrandFatherLeft) {
                    grandFatherNode._leftChild = brotherNode;
                } else {

                    grandFatherNode._rightChild = brotherNode;
                }
            }
            if (brotherNode._parent == null)
                this._rootNode = brotherNode;
            return;
        }
    }
}
