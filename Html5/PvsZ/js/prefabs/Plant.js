Plant = function(state, x, y, data, patch) {
  Phaser.Sprite.call(this, state.game, x, y, data.plantAsset);
  
  this.state = state;
  this.game = state.game;
  this.bullets = state.bullets;
  this.suns = state.suns;

  this.anchor.setTo(0.5);

  this.game.physics.arcade.enable(this);
  this.body.immovable = true;

  this.shootingTimer = this.game.time.create(false);
  this.producingTimer = this.game.time.create(false);

  this.reset(x, y, data, patch);
};

Plant.prototype = Object.create(Phaser.Sprite.prototype);
Plant.prototype.constructor = Plant;

Plant.prototype.reset = function(x, y, data, patch){
  Phaser.Sprite.prototype.reset.call(this, x, y, data.health);

  this.loadTexture(data.plantAsset);

  this.animationName = null;
  if(data.animationFrames) {
    this.animationName = data.plantAsset + 'Anim';
    this.animations.add(this.animationName, data.animationFrames, 6, false);    
  }

  this.isShooter = data.isShooter;
  this.isSunProducer = data.isSunProducer;
  this.patch = patch;

  if(this.isShooter) {
    this.shootingTimer.start();
    this.scheduleShooting();
  }

  if(this.isSunProducer) {
    this.producingTimer.start();
    this.scheduleProduction();
  }
};

Plant.prototype.kill = function() {
  Phaser.Sprite.prototype.kill.call(this);

  this.shootingTimer.stop();
  this.producingTimer.stop();

  this.patch.isBusy = false;
};

Plant.prototype.scheduleShooting = function() {
  this.shoot();

  this.shootingTimer.add(Phaser.Timer.SECOND, this.scheduleShooting, this);
};

Plant.prototype.shoot = function() {

  if(this.animations.getAnimation(this.animationName)) {
    this.play(this.animationName);
  }

  var y = this.y - 10;

  var newElement = this.bullets.getFirstDead();

  if(!newElement) {
    newElement = new Bullet(this, this.x, y);
    this.bullets.add(newElement);
  }
  else {
    newElement.reset(this.x, y);
  }

  newElement.body.velocity.x = 100;
};

Plant.prototype.scheduleProduction = function() {
  this.produceSun();

  this.producingTimer.add(Phaser.Timer.SECOND * 5, this.scheduleProduction, this);
};

Plant.prototype.produceSun = function() {
  var diffX = -40 + Math.random() * 80;
  var diffY = -40 + Math.random() * 80;
  this.state.createSun(this.x + diffX, this.y + diffY);
};
