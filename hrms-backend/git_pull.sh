#!/bin/bash
#拉取分支
#exit
branch="hzh"

git fetch origin $branch
git checkout -b $branch origin/$branch
git pull origin $branch
