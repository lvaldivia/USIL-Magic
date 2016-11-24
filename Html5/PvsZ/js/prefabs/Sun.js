Sun = function(state, x, y) {
  Phaser.Sprite.call(this, state.game, x, y, 'sun');

  this.state = state;
  this.game = state.game;
  this.game.physics.arcade.enable(this);

  this.animations.add('shine', [0,1,0], 10, true);
  this.play('shine');
  this.anchor.setTo(0.5);
  this.inputEnabled = true;
  this.input.pixelPerfectClick = true;
  this.events.onInputDown.add(function(){
    this.state.increaseSun(25);
    this.kill();
  }, this);
  this.sunExpirationTimer = this.game.time.create(false);
  
  this.reset(x, y);
};

Sun.prototype = Object.create(Phaser.Sprite.prototype);
Sun.prototype.constructor = Sun;

Sun.prototype.scheduleExpiration = function(){
  this.sunExpirationTimer.start();
  var expirationTime = 2 + Math.random() * 4;

  this.sunExpirationTimer.add(Phaser.Timer.SECOND * expirationTime, function(){
    this.kill();
  }, this);
};

Sun.prototype.kill = function() {
  this.sunExpirationTimer.stop();

  Phaser.Sprite.prototype.kill.call(this);
};

Sun.prototype.reset = function(x, y) {
  Phaser.Sprite.prototype.reset.call(this, x, y);

  this.scheduleExpiration();
}
