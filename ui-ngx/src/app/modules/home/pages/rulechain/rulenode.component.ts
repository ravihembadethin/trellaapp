///
/// Copyright © 2016-2021 The Thingsboard Authors
///
/// Licensed under the Apache License, Version 2.0 (the "License");
/// you may not use this file except in compliance with the License.
/// You may obtain a copy of the License at
///
///     http://www.apache.org/licenses/LICENSE-2.0
///
/// Unless required by applicable law or agreed to in writing, software
/// distributed under the License is distributed on an "AS IS" BASIS,
/// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
/// See the License for the specific language governing permissions and
/// limitations under the License.
///

import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';
import { Component, OnInit } from '@angular/core';
import { FcNodeComponent } from 'ngx-flowchart/dist/ngx-flowchart';
import { FcRuleNode, RuleNodeType } from '@shared/models/rule-node.models';
import { Router } from '@angular/router';

@Component({
  // tslint:disable-next-line:component-selector
  selector: 'rule-node',
  templateUrl: './rulenode.component.html',
  styleUrls: ['./rulenode.component.scss']
})
export class RuleNodeComponent extends FcNodeComponent implements OnInit {

  iconUrl: SafeResourceUrl;
  RuleNodeType = RuleNodeType;

  constructor(private sanitizer: DomSanitizer,
              private router: Router) {
    super();
  }

  ngOnInit(): void {
    super.ngOnInit();
    if (this.node.iconUrl) {
      this.iconUrl = this.sanitizer.bypassSecurityTrustResourceUrl(this.node.iconUrl);
    }
  }

  openRuleChain($event: Event, node: FcRuleNode) {
    if ($event) {
      $event.stopPropagation();
    }
    if (node.targetRuleChainId) {
      this.router.navigateByUrl(`/thresholds&Notifications/${node.targetRuleChainId}`);
    }
  }
}
