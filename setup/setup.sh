#!/usr/bin/env bash
cp setup/pre-commit .git/hooks/pre-commit
git config --add checkstyle.jar 'config/checkstyle/checkstyle-8.39-all.jar'
git config --add checkstyle.checkfile 'config/checkstyle/checkstyle.xml'

